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

import QuizApp.backend.dtos.ResultDto;
import lombok.RequiredArgsConstructor;
import QuizApp.backend.services.ResultService;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @PostMapping(value="/create")
    public ResponseEntity<ResultDto> createAPI(@RequestBody ResultDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(resultService.create(dto));
    }
    @PutMapping(value="/update/{id}")
    public ResponseEntity<ResultDto> updateAPI(@PathVariable Long id, @RequestBody ResultDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(resultService.update(id, dto));
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<ResultDto> deleteAPI(@PathVariable Long id){
        resultService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(value="/all-result")
    public ResponseEntity<List<ResultDto>> getAllAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(resultService.getAll());
    }
}