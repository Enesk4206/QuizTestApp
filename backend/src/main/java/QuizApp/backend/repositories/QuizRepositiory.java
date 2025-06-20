package QuizApp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizApp.backend.models.Quiz;

public interface QuizRepositiory extends JpaRepository<Quiz, Long> {
}
