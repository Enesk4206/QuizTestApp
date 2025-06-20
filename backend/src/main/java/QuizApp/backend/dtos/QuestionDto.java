package QuizApp.backend.dtos;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    private Long quiz_id;
    private Set<String> questionType = new HashSet<>();
}
