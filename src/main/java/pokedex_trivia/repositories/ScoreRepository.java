package pokedex_trivia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokedex_trivia.models.database_models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Score.Id> {}
