package QuizApp.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import QuizApp.backend.dtos.CategoryDto;
import QuizApp.backend.models.Category;
import QuizApp.backend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryDto createCategory(CategoryDto request){

        if(categoryRepository.existsByName(request.getName())){
            throw new RuntimeException("Category name exists already!");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        
        Category saved = categoryRepository.save(category);

        return maptoResponseCategortDto(saved);
    }

    public List<CategoryDto> listCategories(){
        return categoryRepository.findAll().stream().map(this::maptoResponseCategortDto).collect(Collectors.toList());
    }


    public CategoryDto updateCategory(Long id , CategoryDto request){
        
        Category existsCategory = categoryRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Category not found with : "+id)
        );

        existsCategory.setName(request.getName());
        existsCategory.setDescription(request.getDescription());

        Category updated = categoryRepository.save(existsCategory);
        return maptoResponseCategortDto(updated);


    }

    public void deleteCategory(Long id){
        if(!categoryRepository.existsById(id)){
            throw new RuntimeException("Category not found with id: "+id);
        }

        categoryRepository.deleteById(id);
    }

    public CategoryDto maptoResponseCategortDto(Category category){
        return new CategoryDto(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }
}
