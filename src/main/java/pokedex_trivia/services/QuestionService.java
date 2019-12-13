package pokedex_trivia.services;

import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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
        {
          questionBuilder.stem("Who's that pokémon?");
          Pokemon chosenPokemon = getRandomPokemon();
          Set<Pokemon> wrongPokemon =
              getRandomPokemonExcept(Collections.singleton(chosenPokemon.getId()), 3L);
          questionBuilder.imageUrl(chosenPokemon.getSprites().getFrontDefault());
          alternatives.add(
              AlternativeDto.builder().text(chosenPokemon.getName()).correct(true).build());
          alternatives.addAll(
              wrongPokemon
                  .stream()
                  .map(
                      pokemon ->
                          AlternativeDto.builder().text(pokemon.getName()).correct(false).build())
                  .collect(Collectors.toSet()));
          break;
        }
      case POKEMON_TYPES:
        {
          Set<Long> chosenDexIndexes = Sets.newHashSet();
          Pokemon chosenPokemon = getRandomPokemon();
          chosenDexIndexes.add(chosenPokemon.getId());
          Set<Pokemon> tempWrongPokemon = getRandomPokemonExcept(chosenDexIndexes, 3L);
          List<String> types =
              chosenPokemon
                  .getTypes()
                  .stream()
                  .map(type -> type.getType().getName())
                  .collect(Collectors.toList());
          StringBuilder stem = new StringBuilder("Which of these pokémon is a ");
          if (types.size() > 1) {
            stem.append(String.join("/", types));
          } else {
            stem.append("pure ").append(types.get(0));
          }
          stem.append(" type?");
          Set<Pokemon> wrongPokemon = Sets.newHashSet();
          while (tempWrongPokemon.size() > 0) {
            tempWrongPokemon.forEach(
                pokemon -> {
                  chosenDexIndexes.add(pokemon.getId());
                  if (!CollectionUtils.isEqualCollection(
                      pokemon
                          .getTypes()
                          .stream()
                          .map(type -> type.getType().getName())
                          .collect(Collectors.toSet()),
                      types)) {
                    wrongPokemon.add(pokemon);
                  }
                });
            tempWrongPokemon = getRandomPokemonExcept(chosenDexIndexes, 3L - wrongPokemon.size());
          }
          alternatives.add(
              AlternativeDto.builder().text(chosenPokemon.getName()).correct(true).build());
          alternatives.addAll(
              wrongPokemon
                  .stream()
                  .map(
                      pokemon ->
                          AlternativeDto.builder().text(pokemon.getName()).correct(false).build())
                  .collect(Collectors.toSet()));
          questionBuilder.alternatives(alternatives);
          questionBuilder.stem(stem.toString());
          break;
        }
      case POKEMON_ABILITIES:
        Set<Long> chosenDexIndexes = Sets.newHashSet();
        Pokemon chosenPokemon = getRandomPokemon();
        chosenDexIndexes.add(chosenPokemon.getId());
        Set<Pokemon> tempWrongPokemon = getRandomPokemonExcept(chosenDexIndexes, 3L);
        List<String> abilities =
            chosenPokemon
                .getAbilities()
                .stream()
                .map(ability -> ability.getAbility().getName())
                .collect(Collectors.toList());
        Collections.shuffle(abilities);
        String stem = "Which of these pokémon has the ability " + abilities.get(0) + "?";
        Set<Pokemon> wrongPokemon = Sets.newHashSet();
        while (tempWrongPokemon.size() > 0) {
          tempWrongPokemon.forEach(
              pokemon -> {
                chosenDexIndexes.add(pokemon.getId());
                if (!pokemon
                    .getAbilities()
                    .stream()
                    .map(ability -> ability.getAbility().getName())
                    .collect(Collectors.toSet())
                    .contains(abilities.get(0))) {
                  wrongPokemon.add(pokemon);
                }
              });
          tempWrongPokemon = getRandomPokemonExcept(chosenDexIndexes, 3L - wrongPokemon.size());
        }
        alternatives.add(
            AlternativeDto.builder().text(chosenPokemon.getName()).correct(true).build());
        alternatives.addAll(
            wrongPokemon
                .stream()
                .map(
                    pokemon ->
                        AlternativeDto.builder().text(pokemon.getName()).correct(false).build())
                .collect(Collectors.toSet()));
        questionBuilder.alternatives(alternatives);
        questionBuilder.stem(stem);
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

  private Pokemon getRandomPokemon() throws Exception {
    Long randomDexNumber = (long) RANDOM.nextInt(808);
    return pokeApiFacade.getPokemonByDexIndex(randomDexNumber);
  }

  private Set<Pokemon> getRandomPokemonExcept(Set<Long> except, Long quant) throws Exception {
    Set<Long> chosenDexIndexes = Sets.newHashSet();
    chosenDexIndexes.addAll(except);
    Set<Pokemon> chosenPokemon = Sets.newHashSet();
    Long randomDexNumber = (long) RANDOM.nextInt(808);
    for (int i = 0; i < quant; i++) {
      while (chosenDexIndexes.contains(randomDexNumber)) {
        randomDexNumber = (long) RANDOM.nextInt(808);
      }
      chosenDexIndexes.add(randomDexNumber);
      Pokemon randomPokemon = pokeApiFacade.getPokemonByDexIndex(randomDexNumber);
      chosenPokemon.add(randomPokemon);
    }
    return chosenPokemon;
  }
}
