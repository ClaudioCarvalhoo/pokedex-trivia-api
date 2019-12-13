package pokedex_trivia.pokeapi.models.items.items;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import pokedex_trivia.pokeapi.models.utility.common_models.Name;
import pokedex_trivia.pokeapi.models.utility.common_models.NamedApiResource;

@Builder
@Value
public class ItemCategory {
  Long id;
  String name;
  List<NamedApiResource> items;
  List<Name> names;
  List<NamedApiResource> pokemonSpecies;
}
