package pl.chopy.gwizdbackenddeploy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.chopy.gwizdbackenddeploy.model.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
