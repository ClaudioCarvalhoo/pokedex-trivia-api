package pokedex_trivia.mappers;

import java.util.stream.Collectors;
import pokedex_trivia.models.Category;
import pokedex_trivia.models.CategorySnapshot;
import pokedex_trivia.models.dtos.CategoryDto;

public class CategoryMapper {
  public static CategoryDto CategoryToDto(Category category) {
    return CategoryDto.builder()
        .name(category.getName())
        .subcategories(
            category
                .getSubcategories()
                .stream()
                .map(CategoryMapper::CategoryToDto)
                .collect(Collectors.toSet()))
        .build();
  }

  public static CategoryDto CategorySnapshotToDto(CategorySnapshot categorySnapshot) {
    return CategoryDto.builder()
        .name(categorySnapshot.getName())
        .subcategories(
            categorySnapshot
                .getSubcategories()
                .stream()
                .map(CategoryMapper::CategorySnapshotToDto)
                .collect(Collectors.toSet()))
        .build();
  }
}
