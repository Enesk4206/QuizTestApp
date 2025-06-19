package QuizApp.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizApp.backend.models.User;

public interface  UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);
    boolean existsByUsername(String  username);
    boolean existsByEmail(String email);
}
