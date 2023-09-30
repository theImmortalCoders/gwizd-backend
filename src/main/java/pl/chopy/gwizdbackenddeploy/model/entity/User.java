package pl.chopy.gwizdbackenddeploy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.chopy.gwizdbackenddeploy.model.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails, OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String googleId;
    @NotNull
    private String username;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Email
    @Column(unique = true)
    private String email;
    private String photo;
    @OneToMany(fetch = FetchType.EAGER)
    List<Achievement> achievements;
    private boolean isActive = true;

    public User() {

    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", id);
        attributes.put("sub", googleId);
        attributes.put("email", email);
        attributes.put("role", role);
        attributes.put("login", username);
        attributes.put("achievements", achievements);
        attributes.put("avatar_url", photo);
        attributes.put("picture", photo);
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }


}