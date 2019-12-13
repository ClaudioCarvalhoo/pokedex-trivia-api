package pokedex_trivia.pokeapi.models.items.items;

import lombok.Builder;
import lombok.Value;
import pokedex_trivia.pokeapi.models.utility.common_models.ApiResource;
import pokedex_trivia.pokeapi.models.utility.common_models.NamedApiResource;

@Builder
@Value
public class MachineVersionDetail {
  ApiResource machine;
  NamedApiResource versionGroup;
}
