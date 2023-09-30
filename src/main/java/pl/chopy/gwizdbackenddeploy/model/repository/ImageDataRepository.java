package pl.chopy.gwizdbackenddeploy.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.chopy.gwizdbackenddeploy.model.entity.ImageData;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String fileName);

    boolean existsByName(String name);
}
