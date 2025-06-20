package QuizApp.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import QuizApp.backend.dtos.ResultDto;
import QuizApp.backend.models.Quiz;
import QuizApp.backend.models.Result;
import QuizApp.backend.models.User;
import QuizApp.backend.repositories.QuizRepositiory;
import QuizApp.backend.repositories.ResultRepository;
import QuizApp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final QuizRepositiory quizRepositiory;



    public ResultDto create(ResultDto request){
        User user = userRepository.findById(request.getUser_id()).orElseThrow(
            ()-> new RuntimeException("User not found!")
        );

        Quiz quiz = quizRepositiory.findById(request.getQuiz_id()).orElseThrow(
            ()-> new RuntimeException("Quiz not found!")
        );

        Result result = new Result();
        result.setUser(user);
        result.setQuiz(quiz);
        result.setScore(request.getScore());
        
        Result created = resultRepository.save(result);

        return mapToResultDto(created);
    }


    public ResultDto update(Long id, ResultDto request){
        Result existResult = resultRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Result not found!")
        );
        User user = userRepository.findById(request.getUser_id()).orElseThrow(
            ()-> new RuntimeException("User not found!")
        );
        
        Quiz quiz = quizRepositiory.findById(request.getQuiz_id()).orElseThrow(
            ()-> new RuntimeException("Quiz not found!")
        );

        existResult.setUser(user);
        existResult.setQuiz(quiz);
        existResult.setScore(request.getScore());
        
        Result updated = resultRepository.save(existResult);

        return mapToResultDto(updated);
    }

    public void delete(Long id ){
        if(!resultRepository.existsById(id)){
            throw new RuntimeException("Result not found!");
        }

        resultRepository.deleteById(id);
    }

    public List<ResultDto> getAll(){
        return resultRepository.findAll().stream().map(this::mapToResultDto).collect(Collectors.toList());
    }

    public ResultDto mapToResultDto(Result result){
        return new ResultDto(
            result.getId(),
            result.getUser().getId(),
            result.getQuiz().getId(),
            result.getScore()
        );
    }
}
