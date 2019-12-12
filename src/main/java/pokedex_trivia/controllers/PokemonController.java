package pokedex_trivia.controllers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pokedex_trivia.pokeapi.models.pokemon.pokemon.Pokemon;
import pokedex_trivia.pokeapi.services.PokeApiService;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RestController
public class PokemonController {

  @GetMapping("/pokemon")
  public String getRandomPokemon() throws Exception {
    Random random = new Random();
    int randomDexNumber = random.nextInt(808);
    Gson gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    PokeApiService pokeApiService = retrofit.create(PokeApiService.class);
    final Response<Pokemon> response =
        pokeApiService.getPokemonByDexIndex((long) randomDexNumber).execute();
    if (response.isSuccessful()) {
      return response.body().toString();
    } else {
      return response.errorBody().string();
    }
  }
}
