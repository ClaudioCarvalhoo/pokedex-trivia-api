package pokedex_trivia.pokeapi.models.pokemon.pokemon;

import lombok.Builder;
import lombok.Value;
import pokedex_trivia.pokeapi.models.utility.common_models.NamedApiResource;

@Builder
@Value
public class PokemonStat {
  NamedApiResource stat;
  Long effort;
  Long baseStat;
}
