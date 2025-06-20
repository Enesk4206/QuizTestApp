package QuizApp.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private int age;
    private String phoneNumber;
    private LocalDateTime createdAtDateTime;
    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    protected  void onCreate(){
        createdAtDateTime = LocalDateTime.now();
    }
}
