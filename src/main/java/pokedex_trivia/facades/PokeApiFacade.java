package pokedex_trivia.facades;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import pokedex_trivia.pokeapi.models.items.items.Item;
import pokedex_trivia.pokeapi.models.pokemon.pokemon.Pokemon;
import pokedex_trivia.pokeapi.services.PokeApiService;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PokeApiFacade {
  private final Gson GSON =
      new GsonBuilder()
          .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
          .create();
  private final Retrofit RETROFIT =
      new Retrofit.Builder()
          .baseUrl("https://pokeapi.co/api/v2/")
          .addConverterFactory(GsonConverterFactory.create(GSON))
          .build();
  private final PokeApiService pokeApiService = RETROFIT.create(PokeApiService.class);

  @Cacheable("pokemon")
  public Pokemon getPokemonByDexIndex(Long dexIndex) throws Exception {
    Response<Pokemon> response = pokeApiService.getPokemonByDexIndex(dexIndex).execute();
    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw new RuntimeException(
          "Call for https://pokeapi.co/api/v2/pokemon/"
              + dexIndex
              + "/ has failed!\nError is: "
              + response.errorBody().string());
    }
  }

  @Cacheable("items")
  public Item getItemById(Long itemId) throws Exception {
    Response<Item> response = pokeApiService.getItemById(itemId).execute();
    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw new RuntimeException(
          "Call for https://pokeapi.co/api/v2/item/"
              + itemId
              + "/ has failed!\nError is: "
              + response.errorBody().string());
    }
  }
}
