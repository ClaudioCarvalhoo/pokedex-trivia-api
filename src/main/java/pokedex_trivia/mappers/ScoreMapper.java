package pokedex_trivia.mappers;

import pokedex_trivia.models.database_models.Score;
import pokedex_trivia.models.dtos.ScoreDto;

public class ScoreMapper {
  public static ScoreDto scoreToDto(Score score) {
    return ScoreDto.builder().username(score.getId().getUsername()).score(score.getScore()).build();
  }
}
