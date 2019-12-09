package pokedex_trivia.pokeapi.models.utility.common_models;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class VersionGameIndex {
  Long gameIndex;
  NamedApiResource version;
}
