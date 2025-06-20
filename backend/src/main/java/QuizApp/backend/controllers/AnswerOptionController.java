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

import QuizApp.backend.dtos.AnswerOptionDto;
import QuizApp.backend.services.AnswerOptionService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/answerOption")
@RequiredArgsConstructor
public class AnswerOptionController {
    private final AnswerOptionService answerOptionService;

    @PostMapping(value="/create")
    public ResponseEntity<AnswerOptionDto> createAPI(@RequestBody AnswerOptionDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(answerOptionService.create(dto));
    }
    @PutMapping(value="/update/{id}")
    public ResponseEntity<AnswerOptionDto> updateAPI(@PathVariable Long id, @RequestBody AnswerOptionDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(answerOptionService.update(id, dto));
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<AnswerOptionDto> deleteAPI(@PathVariable Long id){
        answerOptionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(value="/all-answerOptions")
    public ResponseEntity<List<AnswerOptionDto>> getAllAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(answerOptionService.getAll());
    }
}