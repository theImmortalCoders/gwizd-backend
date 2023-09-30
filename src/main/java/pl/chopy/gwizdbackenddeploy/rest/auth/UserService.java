package pl.chopy.gwizdbackenddeploy.rest.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.chopy.gwizdbackenddeploy.model.entity.Achievement;
import pl.chopy.gwizdbackenddeploy.model.mapper.UserMapper;
import pl.chopy.gwizdbackenddeploy.model.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final OidcAuthService authService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserInfoResponse getCurrentUser() {
        return userMapper.map(authService.getCurrentUser());
    }

    public List<Achievement> getAchievementsForUser() {
        return authService.getCurrentUser().getAchievements();
    }
}
