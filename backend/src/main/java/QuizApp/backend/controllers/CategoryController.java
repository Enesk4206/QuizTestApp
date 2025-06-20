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

import QuizApp.backend.dtos.CategoryDto;
import QuizApp.backend.services.CategoryService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(value="/create")
    public ResponseEntity<CategoryDto> createAPI(@RequestBody CategoryDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(dto));
    }
    @PutMapping(value="/update/{id}")
    public ResponseEntity<CategoryDto> updateAPI(@PathVariable Long id, @RequestBody CategoryDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, dto));
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<CategoryDto> deleteAPI(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(value="/all-categories")
    public ResponseEntity<List<CategoryDto>> getAllAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.listCategories());
    }
}
