package hello.numberone.data.repository;

import hello.numberone.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String Email);
}
