package QuizApp.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name="categories_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name="instructor_id")
    private User instructor;
    private String difficulty;
    private boolean isActive;
    private LocalDateTime createdDateTime;

    @PrePersist
    protected void onCreate(){
        createdDateTime = LocalDateTime.now();
    }
}
