package QuizApp.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import QuizApp.backend.dtos.UserAnswerDto;
import QuizApp.backend.models.Question;
import QuizApp.backend.models.User;
import QuizApp.backend.models.UserAnswer;
import QuizApp.backend.repositories.QuestionRepository;
import QuizApp.backend.repositories.UserAnswerRepository;
import QuizApp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserAnswerService{
    private final UserAnswerRepository userAnswerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    //create

    public UserAnswerDto create(UserAnswerDto request){
        
        User user = userRepository.findById(request.getUser_id()).orElseThrow(
            ()-> new RuntimeException("User id not found!")
        );

        Question question = questionRepository.findById(request.getQuestion_id()).orElseThrow(
            () -> new RuntimeException("User not found!")
        );

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(user);
        userAnswer.setQuestion(question);
        userAnswer.setSelectedAnswer(request.getSelectedAnswer());
        
        UserAnswer created = userAnswerRepository.save(userAnswer);
        return mapUserAnswerDto(created);

    }

    public UserAnswerDto update(Long id, UserAnswerDto request){
        
        UserAnswer existUserAnswer = userAnswerRepository.findById(id).orElseThrow(
            () -> new RuntimeException("User answer not found!!")
        );

        User user = userRepository.findById(request.getUser_id()).orElseThrow(
            ()-> new RuntimeException("User id not found!")
        );

        Question question = questionRepository.findById(request.getQuestion_id()).orElseThrow(
            () -> new RuntimeException("User not found!")
        );

        existUserAnswer.setUser(user);
        existUserAnswer.setQuestion(question);
        existUserAnswer.setSelectedAnswer(request.getSelectedAnswer());
        
        UserAnswer updated = userAnswerRepository.save(existUserAnswer);
        return mapUserAnswerDto(updated);

    }

    public List<UserAnswerDto> getAll(){
        return userAnswerRepository.findAll().stream().map(this::mapUserAnswerDto).collect(Collectors.toList());
    }

    public void delete(Long id){
        if(!userAnswerRepository.existsById(id)){
            throw new RuntimeException("User answer not found!");
        }

        userAnswerRepository.deleteById(id);
    }



    public UserAnswerDto mapUserAnswerDto(UserAnswer userAnswer){
        return new UserAnswerDto(
            userAnswer.getId(),
            userAnswer.getUser().getId(),
            userAnswer.getQuestion().getId(),
            userAnswer.getSelectedAnswer()
        );
    }
}
