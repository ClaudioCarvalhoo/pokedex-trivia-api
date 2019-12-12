package pokedex_trivia.facades;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pokedex_trivia.models.database_models.Score;
import pokedex_trivia.models.dtos.ScoreDto;
import pokedex_trivia.repositories.ScoreRepository;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ScoreFacade {
  private ScoreRepository scoreRepository;

  @Transactional
  public void addScoreToRoom(Long roomId, ScoreDto scoreDto) {
    Score score = new Score();
    score.setId(new Score.Id(roomId, scoreDto.getUsername()));
    score.setScore(scoreDto.getScore());
    scoreRepository.save(score);
  }
}
