package QuizApp.backend.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="questions")

public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Quiz quiz;
    private Set<String> questionType = new HashSet<>();
    private LocalDateTime createdDateTime;

    @PrePersist
    protected void onCreate(){
        createdDateTime = LocalDateTime.now();
    }
}
