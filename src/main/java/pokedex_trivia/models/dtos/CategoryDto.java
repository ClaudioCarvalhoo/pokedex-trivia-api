package pokedex_trivia.models.dtos;

import java.util.Set;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CategoryDto {
  @NonNull String id;
  @NonNull String name;
  @NonNull String shortName;
  Set<CategoryDto> subcategories;
}
