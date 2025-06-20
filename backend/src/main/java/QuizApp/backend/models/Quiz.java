package QuizApp.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="quizes")
public class Quiz {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Category category;
    private User createdByUser;
    private String difficulty;
    private LocalDateTime createdDateTime;

    @PrePersist
    protected void onCreate(){
        createdDateTime = LocalDateTime.now();
    }
}
