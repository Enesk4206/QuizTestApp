package QuizApp.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDto {
    private Long id;
    private Long user_id;
    private Long question_id;
    private String selectedAnswer;
}
