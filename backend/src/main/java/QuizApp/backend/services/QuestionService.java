package QuizApp.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import QuizApp.backend.dtos.QuestionDto;
import QuizApp.backend.models.Question;
import QuizApp.backend.models.Quiz;
import QuizApp.backend.repositories.QuestionRepository;
import QuizApp.backend.repositories.QuizRepositiory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepositiory quizRepositiory;

    public QuestionDto create(QuestionDto request){
        Quiz quiz = quizRepositiory.findById(request.getId()).orElseThrow(
            ()-> new RuntimeException("Quiz not found!")
        );

        Question question = new Question();
        question.setQuiz(quiz);
        question.setQuestionType(request.getQuestionType());
        
        Question created = questionRepository.save(question);

        return new QuestionDto(
            created.getId(),
            created.getQuiz().getId(),
            created.getQuestionType()
        );
    }
    
    public QuestionDto update(Long id, QuestionDto request){
        Question existQuestion  = questionRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Question not found!")
        );

        Quiz quiz = quizRepositiory.findById(request.getQuiz_id()).orElseThrow(
            ()-> new RuntimeException("Quiz not found!")
        );

        existQuestion.setQuiz(quiz);
        existQuestion.setQuestionType(request.getQuestionType());

        Question updated = questionRepository.save(existQuestion);

        return new QuestionDto(
            updated.getId(),
            updated.getQuiz().getId(),
            updated.getQuestionType()
        );

    }

    public List<QuestionDto> getAllQuestions(){
        
        List<Question> questions = questionRepository.findAll();

        return questions.stream().map(
            question -> new QuestionDto(
                question.getId(),
                question.getQuiz().getId(),
                question.getQuestionType()
            )
        ).collect(Collectors.toList());
    }

    public void delete (Long id){
        if(!questionRepository.existsById(id)){
           throw new RuntimeException("Question not found!");
        }

        questionRepository.deleteById(id);
    }


}
