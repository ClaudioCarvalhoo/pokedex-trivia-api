package pokedex_trivia.mappers;

import pokedex_trivia.models.Category;
import pokedex_trivia.models.dtos.CategoryDto;

import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryDto CategoryToDto(Category category){
         CategoryDto.CategoryDtoBuilder builder =
                 CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                 .subcategories(category.getSubcategories().stream().map(CategoryMapper::CategoryToDto).collect(Collectors.toSet()));
         if (category.getParentCategory() != null){
             builder.parentCategoryId(category.getParentCategory().getId());
         }
         return builder.build();
    }
}
