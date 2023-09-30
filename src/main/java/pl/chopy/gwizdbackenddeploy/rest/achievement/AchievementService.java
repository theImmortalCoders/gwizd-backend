package pl.chopy.gwizdbackenddeploy.rest.achievement;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.chopy.gwizdbackenddeploy.model.entity.Achievement;
import pl.chopy.gwizdbackenddeploy.model.mapper.AchievementMapper;
import pl.chopy.gwizdbackenddeploy.model.repository.AchievementRepository;
import pl.chopy.gwizdbackenddeploy.model.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AchievementService {
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;
    private final AchievementMapper achievementMapper;

    public void addAchievement(AchievementAddRequest achievementAddRequest) {
        Option.of(achievementAddRequest)
                .map(achievementMapper::map)
                .map(achievementRepository::save)
                .getOrElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement addAchievement(Long achievId, Long userId) {
        var user = Option.ofOptional(userRepository.findById(userId))
                .getOrElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User '" + userId + "' not found."));
        var achievement = Option.ofOptional(achievementRepository.findById(achievId))
                .getOrElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Achievement '" + achievId + "' not found."));

        var userAchievements = user.getAchievements();
        if (userAchievements.contains(achievement)) {
            return null;
        }
        userAchievements.add(achievement);
        user.setAchievements(userAchievements);
        Option.of(userRepository.save(user))
                .getOrElseThrow(()->new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        return achievement;
    }

}
