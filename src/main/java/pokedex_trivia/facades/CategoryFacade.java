package pokedex_trivia.facades;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex_trivia.mappers.CategoryMapper;
import pokedex_trivia.models.dtos.CategoryDto;
import pokedex_trivia.repositories.CategoryRepository;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryFacade {
  private CategoryRepository categoryRepository;

  public Set<CategoryDto> getAllCategories() {
    return categoryRepository
        .findAllByParentCategoryId(null)
        .stream()
        .map(CategoryMapper::CategoryToDto)
        .collect(Collectors.toSet());
  }
}
