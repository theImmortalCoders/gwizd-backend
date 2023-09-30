package pl.chopy.gwizdbackenddeploy.model.mapper;

import org.mapstruct.Mapper;
import pl.chopy.gwizdbackenddeploy.model.entity.Achievement;
import pl.chopy.gwizdbackenddeploy.rest.achievement.AchievementAddRequest;

@Mapper
public interface AchievementMapper {
    Achievement map(AchievementAddRequest achievementAddRequest);
}
