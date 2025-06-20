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

import QuizApp.backend.services.QuizService;
import lombok.RequiredArgsConstructor;
import QuizApp.backend.dtos.QuizDto;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping(value="/create")
    public ResponseEntity<QuizDto> createAPI(@RequestBody QuizDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(dto));
    }
    @PutMapping(value="/update/{id}")
    public ResponseEntity<QuizDto> updateAPI(@PathVariable Long id, @RequestBody QuizDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.updateQuiz(id, dto));
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<QuizDto> deleteAPI(@PathVariable Long id){
        quizService.deleteQuiz(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(value="/all-quizzes")
    public ResponseEntity<List<QuizDto>> getAllAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getAllQuizzList());
    }
}