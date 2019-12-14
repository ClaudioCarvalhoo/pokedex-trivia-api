package pokedex_trivia.pokeapi.models.utility.common_models;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NamedPagedResources {
  Long count;
  String next;
  String previous;
  List<NamedApiResource> results;
}
