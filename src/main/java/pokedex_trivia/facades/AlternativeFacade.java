package pokedex_trivia.facades;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex_trivia.models.database_models.Alternative;
import pokedex_trivia.repositories.AlternativeRepository;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlternativeFacade {
  private AlternativeRepository alternativeRepository;

  public Map<UUID, Boolean> getCorrectnessOfAlternatives(Set<UUID> alternativeIds) {
    return alternativeRepository
        .findAllById(alternativeIds)
        .stream()
        .collect(Collectors.toMap(Alternative::getId, Alternative::getCorrect));
  }
}
