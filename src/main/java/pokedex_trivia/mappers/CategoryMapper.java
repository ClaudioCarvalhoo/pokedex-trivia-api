package pokedex_trivia.mappers;

import java.util.stream.Collectors;
import pokedex_trivia.models.database_models.Category;
import pokedex_trivia.models.dtos.CategoryDto;

public class CategoryMapper {
  public static CategoryDto CategoryToDto(Category category) {
    return CategoryDto.builder()
        .id(category.getId())
        .name(category.getName())
        .shortName(category.getShortName())
        .subcategories(
            category
                .getSubcategories()
                .stream()
                .map(CategoryMapper::CategoryToDto)
                .collect(Collectors.toSet()))
        .build();
  }
}
