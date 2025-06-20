package QuizApp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizApp.backend.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    
}