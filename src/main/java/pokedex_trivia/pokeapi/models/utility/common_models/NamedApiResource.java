package pokedex_trivia.pokeapi.models.utility.common_models;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NamedApiResource {
    String name;
    String url;
}
