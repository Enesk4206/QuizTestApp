package QuizApp.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import QuizApp.backend.dtos.AnswerOptionDto;
import QuizApp.backend.models.AnswerOption;
import QuizApp.backend.models.Question;
import QuizApp.backend.repositories.AnswerOptionRepository;
import QuizApp.backend.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerOptionService {
    private final AnswerOptionRepository answerOptionRepository;
    private final QuestionRepository questionRepository;
    
    public AnswerOptionDto create(AnswerOptionDto request){
        Question question = questionRepository.findById(request.getQuestion_id()).orElseThrow(
            ()-> new RuntimeException("Question not found!")
        );

        AnswerOption answerOption =new AnswerOption();
        answerOption.setQuestion(question);
        answerOption.setText(request.getText());
        answerOption.setCorrect(request.isCorrect());

        AnswerOption created = answerOptionRepository.save(answerOption);
        return mapToAnswerOptionDto(created);

    }


    public AnswerOptionDto update(Long id, AnswerOptionDto request){
        AnswerOption existAnswerOption = answerOptionRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Answer Option not found!")
        );
        
        Question question = questionRepository.findById(request.getQuestion_id()).orElseThrow(
            ()-> new RuntimeException("Question not found!")
        );

        existAnswerOption.setQuestion(question);
        existAnswerOption.setText(request.getText());
        existAnswerOption.setCorrect(request.isCorrect());

        AnswerOption updated = answerOptionRepository.save(existAnswerOption);
        return mapToAnswerOptionDto(updated);

    } 

    public List<AnswerOptionDto> getAll(){
        return answerOptionRepository.findAll().stream().map(this::mapToAnswerOptionDto).collect(Collectors.toList());
    }

    public void delete(Long id ){
        if(!answerOptionRepository.existsById(id)){

            throw new RuntimeException("Answer Option not found!");
        }
    }



    public AnswerOptionDto mapToAnswerOptionDto(AnswerOption answerOption){
        return new AnswerOptionDto(
            answerOption.getId(),
            answerOption.getQuestion().getId(),
            answerOption.getText(),
            answerOption.isCorrect()
        );
    }
}
