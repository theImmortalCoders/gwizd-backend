package pl.chopy.gwizdbackenddeploy.rest.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.chopy.gwizdbackenddeploy.model.mapper.UserMapper;

@Service
@AllArgsConstructor
public class UserService {
    private final OidcAuthService authService;
    private final UserMapper userMapper;

    public UserInfoResponse getCurrentUser() {
        return userMapper.map(authService.getCurrentUser());
    }

}
