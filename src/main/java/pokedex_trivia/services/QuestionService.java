package pokedex_trivia.services;

import com.google.common.collect.Sets;
import java.util.Random;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex_trivia.facades.PokeApiFacade;
import pokedex_trivia.models.CategoryId;
import pokedex_trivia.models.dtos.AlternativeDto;
import pokedex_trivia.models.dtos.QuestionDto;
import pokedex_trivia.pokeapi.models.pokemon.pokemon.Pokemon;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionService {
  private final Random RANDOM = new Random();

  private PokeApiFacade pokeApiFacade;

  public QuestionDto createQuestion(String categoryId) throws Exception {
    QuestionDto.QuestionDtoBuilder questionBuilder = QuestionDto.builder();
    Set<AlternativeDto> alternatives = Sets.newHashSet();
    switch (CategoryId.fromString(categoryId)) {
      case POKEMON_NAMES:
        questionBuilder.stem("Quem é esse pokémon?");
        Set<Long> chosenDexIndexes = Sets.newHashSet();
        Long randomDexNumber = (long) RANDOM.nextInt(808);
        chosenDexIndexes.add(randomDexNumber);
        Pokemon chosenPokemon = pokeApiFacade.getPokemonByDexIndex(randomDexNumber);
        questionBuilder.imageUrl(chosenPokemon.getSprites().getFrontDefault());
        alternatives.add(
            AlternativeDto.builder().text(chosenPokemon.getName()).correct(true).build());
        for (int i = 0; i < 3; i++) {
          while (chosenDexIndexes.contains(randomDexNumber)) {
            randomDexNumber = (long) RANDOM.nextInt(808);
          }
          chosenDexIndexes.add(randomDexNumber);
          chosenPokemon = pokeApiFacade.getPokemonByDexIndex(randomDexNumber);
          alternatives.add(
              AlternativeDto.builder().text(chosenPokemon.getName()).correct(false).build());
        }
      case POKEMON_TYPES:
        break;
      case POKEMON_ABILITIES:
        break;
      case ITEM_NAMES:
        break;
      case ITEM_EFFECTS:
        break;
      default:
        throw new RuntimeException("Received unknown category.");
    }
    return questionBuilder.alternatives(alternatives).build();
  }
}
