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

import QuizApp.backend.dtos.UserAnswerDto;
import lombok.RequiredArgsConstructor;
import QuizApp.backend.services.UserAnswerService;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/userAnswer")
@RequiredArgsConstructor
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    @PostMapping(value="/create")
    public ResponseEntity<UserAnswerDto> createAPI(@RequestBody UserAnswerDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userAnswerService.create(dto));
    }
    @PutMapping(value="/update/{id}")
    public ResponseEntity<UserAnswerDto> updateAPI(@PathVariable Long id, @RequestBody UserAnswerDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(userAnswerService.update(id, dto));
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<UserAnswerDto> deleteAPI(@PathVariable Long id){
        userAnswerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(value="/all-userAnswer")
    public ResponseEntity<List<UserAnswerDto>> getAllAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(userAnswerService.getAll());
    }
}