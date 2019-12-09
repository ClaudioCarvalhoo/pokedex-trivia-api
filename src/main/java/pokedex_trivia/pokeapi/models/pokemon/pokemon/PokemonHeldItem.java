package pokedex_trivia.pokeapi.models.pokemon.pokemon;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import pokedex_trivia.pokeapi.models.utility.common_models.NamedApiResource;

@Builder
@Value
public class PokemonHeldItem {
  NamedApiResource item;
  List<PokemonHeldItemVersion> versionDetails;
}
