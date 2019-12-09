package pokedex_trivia.models.dtos;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class CategoryDto {
    UUID id;
    @NonNull String name;
    UUID parentCategoryId;
    Set<CategoryDto> subcategories;
}
