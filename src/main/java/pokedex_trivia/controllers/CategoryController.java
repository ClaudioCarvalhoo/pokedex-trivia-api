package pokedex_trivia.controllers;

import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pokedex_trivia.models.dtos.CategoryDto;
import pokedex_trivia.services.CategoryService;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
  private CategoryService categoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Set<CategoryDto> getAllCategories() {
    return categoryService.getAllCategories();
  }
}
