package pokedex_trivia.pokeapi.services;

import pokedex_trivia.pokeapi.models.items.items.Item;
import pokedex_trivia.pokeapi.models.pokemon.pokemon.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiService {
  @GET("pokemon/{dexIndex}/")
  Call<Pokemon> getPokemonByDexIndex(@Path("dexIndex") Long dexIndex);

  @GET("item/{itemId}/")
  Call<Item> getItemById(@Path("itemId") Long itemId);
}
