package pokedex_trivia.models.dtos;

import java.util.Set;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RoomSummaryDto {
  @NonNull Long id;
  @NonNull Set<CategoryDto> categories;
}
