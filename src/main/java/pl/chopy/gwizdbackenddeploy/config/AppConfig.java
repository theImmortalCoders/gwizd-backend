package pl.chopy.gwizdbackenddeploy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Configuration
    @Data
    @ConfigurationProperties(prefix = "gwizd.security.auth")
    public static class SecurityAuthProperties {
        private String loginUri;
        private String redirectUri;
        private String logoutUri;
    }
}
