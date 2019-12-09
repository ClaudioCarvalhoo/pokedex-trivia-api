package pokedex_trivia.pokeapi.models.pokemon.pokemon;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PokemonSprites {
  String frontDefault;
  String frontShiny;
  String frontFemale;
  String FrontShinyFemale;
  String backDefault;
  String backShiny;
  String backFemale;
  String backShinyFemale;
}
