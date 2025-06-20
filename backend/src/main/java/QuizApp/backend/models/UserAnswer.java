package QuizApp.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="userAnswers")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private User user;
    private Question question;
    private String selectedAnswer;
    private LocalDateTime answeredAtDateTime;

    @PrePersist
    protected void onCreate(){
        answeredAtDateTime =  LocalDateTime.now();
    }
}
