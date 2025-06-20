package QuizApp.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import QuizApp.backend.dtos.QuizDto;
import QuizApp.backend.models.Category;
import QuizApp.backend.models.Quiz;
import QuizApp.backend.models.User;
import QuizApp.backend.repositories.CategoryRepository;
import QuizApp.backend.repositories.QuizRepositiory;
import QuizApp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepositiory quizRepositiory;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    //create

    public QuizDto createQuiz(QuizDto request){
        Category category = categoryRepository.findById(request.getCategory_id()).orElseThrow(
            () -> new RuntimeException("Category not founda!!")
        );

        User instructor = userRepository.findById(request.getInstructor_id()).orElseThrow(
            ()-> new RuntimeException("Instructor id not found!")
        );

        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setCategory(category);
        quiz.setInstructor(instructor);
        quiz.setDifficulty(request.getDifficulty());
        quiz.setActive(request.isActive());
        
        Quiz saved = quizRepositiory.save(quiz);
        return mapToQuiz(saved);

    }

    public QuizDto updateQuiz(Long id, QuizDto request){
        Quiz existsQuiz = quizRepositiory.findById(id).orElseThrow(
            ()-> new RuntimeException("Quiz wasn't found!")
        );
        
        Category category = categoryRepository.findById(request.getCategory_id()).orElseThrow(
            () -> new RuntimeException("Category not founda!!")
        );

        User instructor = userRepository.findById(request.getInstructor_id()).orElseThrow(
            ()-> new RuntimeException("Instructor id not found!")
        );

        existsQuiz.setTitle(request.getTitle());
        existsQuiz.setDescription(request.getDescription());
        existsQuiz.setCategory(category);
        existsQuiz.setInstructor(instructor);
        existsQuiz.setDifficulty(request.getDifficulty());
        existsQuiz.setActive(request.isActive());
        
        Quiz updated = quizRepositiory.save(existsQuiz);
        return mapToQuiz(updated);

    }

    public List<QuizDto> getAllQuizzList(){
        return quizRepositiory.findAll().stream().map(this::mapToQuiz).collect(Collectors.toList());
    }

    public void deleteQuiz(Long id){
        if(!quizRepositiory.existsById(id)){
            throw new RuntimeException("Quiz not found!");
        }

        quizRepositiory.deleteById(id);
    }



    public QuizDto mapToQuiz(Quiz quiz){
        return new QuizDto(
            quiz.getId(),
            quiz.getTitle(),
            quiz.getDescription(),
            quiz.getCategory().getId(),
            quiz.getInstructor().getId(),
            quiz.getDifficulty(),
            quiz.isActive()
        );
    }
}
