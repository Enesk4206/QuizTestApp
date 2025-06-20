package QuizApp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizApp.backend.models.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    
}
