package pokedex_trivia.models.dtos;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
@Builder(toBuilder = true)
public class RoomSummaryDto {
    @NonNull Long id;
    @NonNull Set<CategoryDto> categories;
}
