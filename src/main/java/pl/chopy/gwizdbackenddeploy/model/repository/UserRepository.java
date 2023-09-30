package pl.chopy.gwizdbackenddeploy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.chopy.gwizdbackenddeploy.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String email);

    Optional<User> getByEmailOrUsername(String email, String name);
}
