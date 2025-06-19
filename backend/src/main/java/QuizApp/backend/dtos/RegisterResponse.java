package QuizApp.backend.dtos;

import QuizApp.backend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String username;
    // private String password;
    private String email;
    private int age;
    private String phoneNumber;
    private Role role;
    private String token;
}
