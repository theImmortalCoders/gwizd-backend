package pl.chopy.gwizdbackenddeploy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.chopy.gwizdbackenddeploy.model.entity.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
