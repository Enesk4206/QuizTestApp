package QuizApp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizApp.backend.models.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
    
}
