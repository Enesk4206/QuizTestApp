package QuizApp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizApp.backend.models.AnswerOption;

public interface  AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
    
}
