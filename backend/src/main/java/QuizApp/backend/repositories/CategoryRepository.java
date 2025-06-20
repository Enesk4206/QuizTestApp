package QuizApp.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizApp.backend.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
