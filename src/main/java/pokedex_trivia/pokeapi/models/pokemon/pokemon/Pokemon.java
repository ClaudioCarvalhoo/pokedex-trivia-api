package pokedex_trivia.pokeapi.models.pokemon.pokemon;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import pokedex_trivia.pokeapi.models.utility.common_models.NamedApiResource;
import pokedex_trivia.pokeapi.models.utility.common_models.VersionGameIndex;

@Builder
@Value
public class Pokemon {
  Long id;
  String name;
  Long baseExperience;
  Long height;
  boolean isDefault;
  Long order;
  Long weight;
  List<PokemonAbility> abilities;
  List<NamedApiResource> forms;
  List<VersionGameIndex> gameIndices;
  List<PokemonHeldItem> heldItems;
  String locationAreaEncounters;
  List<PokemonMove> moves;
  PokemonSprites sprites;
  NamedApiResource species;
  List<PokemonStat> stats;
  List<PokemonType> types;
}
