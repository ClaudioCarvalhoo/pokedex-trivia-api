package pokedex_trivia.models.dtos;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ScoreDto {
  @NonNull String username;
  @NonNull Long score;
}
