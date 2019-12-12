package pokedex_trivia.models.dtos;

import java.util.UUID;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AlternativeDto {
  UUID id;
  @NonNull String text;
  String imageUrl;
  Boolean correct;
}
