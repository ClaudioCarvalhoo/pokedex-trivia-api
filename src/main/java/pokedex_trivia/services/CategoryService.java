package pokedex_trivia.services;

import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex_trivia.facades.CategoryFacade;
import pokedex_trivia.models.dtos.CategoryDto;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {
  private CategoryFacade categoryFacade;

  public Set<CategoryDto> getAllCategories() {
    return categoryFacade.getAllCategories();
  }
}
