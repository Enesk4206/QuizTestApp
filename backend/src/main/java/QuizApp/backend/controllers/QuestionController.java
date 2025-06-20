package QuizApp.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import QuizApp.backend.dtos.QuestionDto;
import lombok.RequiredArgsConstructor;
import QuizApp.backend.services.QuestionService;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping(value="/create")
    public ResponseEntity<QuestionDto> createAPI(@RequestBody QuestionDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.create(dto));
    }
    @PutMapping(value="/update/{id}")
    public ResponseEntity<QuestionDto> updateAPI(@PathVariable Long id, @RequestBody QuestionDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.update(id, dto));
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<QuestionDto> deleteAPI(@PathVariable Long id){
        questionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(value="/all-questions")
    public ResponseEntity<List<QuestionDto>> getAllAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestions());
    }
}