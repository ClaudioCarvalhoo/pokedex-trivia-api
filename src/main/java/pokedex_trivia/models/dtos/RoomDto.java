package pokedex_trivia.models.dtos;

import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RoomDto {
  Long id;
  Set<String> categories;
  Set<QuestionDto> questions;
  List<ScoreDto> leaderboard;
}
