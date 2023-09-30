package pl.chopy.gwizdbackenddeploy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
