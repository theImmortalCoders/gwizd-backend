package pl.chopy.gwizdbackenddeploy.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.chopy.gwizdbackenddeploy.rest.auth.OidcAuthService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@OpenAPIDefinition(info = @Info(title = "Gwizd API documentation"))
@AllArgsConstructor
public class SecurityConfig {

    private final OidcAuthService oidcAuthService;
    private final AppConfig.SecurityAuthProperties securityAuthProperties;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(
                        "http://localhost:3000",
                        "http://localgost:8080",
                        "http://front.gwizd.online",
                        "http://gwizd.online",
                        "https://gwizd.online",
                        "http://api.gwizd.online",
                        "https://api.gwizd.online",
                        "http://back.gwizd.online"
                ).allowCredentials(true);
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(request -> request
                        .anyRequest()
                        .permitAll())
                .oauth2Login(login -> login
                        .userInfoEndpoint()
                        .oidcUserService(oidcAuthService)
                        .and()
                        .authorizationEndpoint().baseUri(securityAuthProperties.getLoginUri())
                        .and()
                        .successHandler((request, response, authentication) -> {
                            String redirectUrl = Arrays.stream(request.getCookies())
                                    .filter(cookie -> cookie.getName().equals("redirectUrl"))
                                    .findFirst()
                                    .map(Cookie::getValue)
                                    .orElse(securityAuthProperties.getRedirectUri());
                            response.sendRedirect(redirectUrl);
                        }))
                .logout(logout -> logout
                        .logoutUrl(securityAuthProperties.getLogoutUri())
                        .logoutSuccessUrl(securityAuthProperties.getRedirectUri())
                        .invalidateHttpSession(false)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .cors();
        return http.build();
    }
}
