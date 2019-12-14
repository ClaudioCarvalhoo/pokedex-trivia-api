package pokedex_trivia.pokeapi.models.items.items;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import pokedex_trivia.pokeapi.models.utility.common_models.ApiResource;
import pokedex_trivia.pokeapi.models.utility.common_models.GenerationGameIndex;
import pokedex_trivia.pokeapi.models.utility.common_models.Name;
import pokedex_trivia.pokeapi.models.utility.common_models.NamedApiResource;
import pokedex_trivia.pokeapi.models.utility.common_models.VerboseEffect;
import pokedex_trivia.pokeapi.models.utility.common_models.VersionGroupFlavorText;

@Builder
@Value
public class Item {
  Long id;
  String name;
  Long cost;
  Long flingPower;
  NamedApiResource flingEffect;
  List<NamedApiResource> attributes;
  NamedApiResource category;
  List<VerboseEffect> effectEntries;
  List<VersionGroupFlavorText> flavorTextEntries;
  List<GenerationGameIndex> gameIndices;
  List<Name> names;
  ItemSprites sprites;
  List<ItemHolderPokemon> heldByPokemon;
  ApiResource babyTriggerFor;
  List<MachineVersionDetail> machines;
}
