package pokedex_trivia.pokeapi.models.items.items;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ItemSprites {
  @SerializedName("default")
  String defaultSprite;
}
