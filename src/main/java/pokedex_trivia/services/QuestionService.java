package pokedex_trivia.services;

import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex_trivia.facades.PokeApiFacade;
import pokedex_trivia.models.CategoryId;
import pokedex_trivia.models.dtos.AlternativeDto;
import pokedex_trivia.models.dtos.QuestionDto;
import pokedex_trivia.pokeapi.models.items.items.Item;
import pokedex_trivia.pokeapi.models.pokemon.pokemon.Pokemon;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionService {
  private final Random RANDOM = new Random();
  private final Set<Long> BANNED_ITEM_IDS =
      Sets.newHashSet(
              95, 96, 97, 98, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 406, 407,
              413, 415, 416, 417, 418, 428, 429, 430, 431, 432, 433, 435, 436, 437, 438, 439, 440,
              441, 444, 445, 448, 467, 468, 469, 470, 473, 474, 475, 476, 477, 478, 479, 480, 481,
              482, 483, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499,
              500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516,
              517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 530, 533, 534, 535, 537, 538, 539,
              540, 541, 543, 544, 545, 546, 547, 548, 553, 554, 555, 556, 557, 559, 560, 561, 562,
              568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 615, 620, 633, 634, 635,
              636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652,
              653, 654, 655, 656, 657, 658, 663, 664, 665, 666, 676, 677, 678, 679, 691, 692, 693,
              694, 733, 735, 736, 738, 740, 741, 762, 764, 768, 769, 770, 773, 774, 775, 776, 779,
              782, 783, 784, 786, 787, 788, 789, 790, 791, 807, 815, 880, 882, 886, 893, 894, 895,
              919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935,
              936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952,
              953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969,
              970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986,
              987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002,
              1003, 1004, 1005)
          .stream()
          .map(Integer::longValue)
          .collect(Collectors.toSet());

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
        }
        break;
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
              AlternativeDto.builder()
                  .text(chosenPokemon.getName())
                  .imageUrl(chosenPokemon.getSprites().getFrontDefault())
                  .correct(true)
                  .build());
          alternatives.addAll(
              wrongPokemon
                  .stream()
                  .map(
                      pokemon ->
                          AlternativeDto.builder()
                              .text(pokemon.getName())
                              .imageUrl(pokemon.getSprites().getFrontDefault())
                              .correct(false)
                              .build())
                  .collect(Collectors.toSet()));
          questionBuilder.alternatives(alternatives);
          questionBuilder.stem(stem.toString());
        }
        break;
      case POKEMON_ABILITIES:
        {
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
              AlternativeDto.builder()
                  .text(chosenPokemon.getName())
                  .imageUrl(chosenPokemon.getSprites().getFrontDefault())
                  .correct(true)
                  .build());
          alternatives.addAll(
              wrongPokemon
                  .stream()
                  .map(
                      pokemon ->
                          AlternativeDto.builder()
                              .text(pokemon.getName())
                              .imageUrl(pokemon.getSprites().getFrontDefault())
                              .correct(false)
                              .build())
                  .collect(Collectors.toSet()));
          questionBuilder.alternatives(alternatives);
          questionBuilder.stem(stem);
        }
        break;
      case ITEM_NAMES:
        {
          List<Long> possibleItemIds =
              LongStream.range(1, 1005).boxed().collect(Collectors.toList());
          possibleItemIds.removeAll(BANNED_ITEM_IDS);
          questionBuilder.stem("What's the name of that item?");
          Item chosenItem = getRandomItem(possibleItemIds);
          possibleItemIds.remove(chosenItem.getId());
          Set<Item> tempWrongItems = getRandomItems(possibleItemIds, 3L);
          questionBuilder.imageUrl(chosenItem.getSprites().getDefaultSprite());
          Set<Item> wrongItems = Sets.newHashSet();
          while (tempWrongItems.size() > 0) {
            tempWrongItems.forEach(
                item -> {
                  possibleItemIds.remove(item.getId());
                  if (!item.getSprites()
                      .getDefaultSprite()
                      .equals(chosenItem.getSprites().getDefaultSprite())) {
                    wrongItems.add(item);
                  }
                });
            tempWrongItems = getRandomItems(possibleItemIds, 3L - wrongItems.size());
          }
          alternatives.add(
              AlternativeDto.builder()
                  .text(removeTrailingHyphensFromName(chosenItem.getName()))
                  .correct(true)
                  .build());
          alternatives.addAll(
              wrongItems
                  .stream()
                  .map(
                      item ->
                          AlternativeDto.builder()
                              .text(removeTrailingHyphensFromName(item.getName()))
                              .correct(false)
                              .build())
                  .collect(Collectors.toSet()));
        }
        break;
      case ITEM_EFFECTS:
        {
          List<Long> possibleItemIds =
              LongStream.range(1, 1005).boxed().collect(Collectors.toList());
          possibleItemIds.removeAll(BANNED_ITEM_IDS);
          Item chosenItem = getRandomItem(possibleItemIds);
          possibleItemIds.remove(chosenItem.getId());
          questionBuilder.stem(
              "What item has the following effect?\n"
                  + chosenItem.getEffectEntries().get(0).getShortEffect());
          Set<Item> tempWrongItems = getRandomItems(possibleItemIds, 3L);
          Set<Item> wrongItems = Sets.newHashSet();
          while (tempWrongItems.size() > 0) {
            tempWrongItems.forEach(
                item -> {
                  possibleItemIds.remove(item.getId());
                  if (!item.getEffectEntries()
                      .get(0)
                      .getShortEffect()
                      .equals(chosenItem.getEffectEntries().get(0).getShortEffect())) {
                    wrongItems.add(item);
                  }
                });
            tempWrongItems = getRandomItems(possibleItemIds, 3L - wrongItems.size());
          }
          alternatives.add(
              AlternativeDto.builder()
                  .text(removeTrailingHyphensFromName(chosenItem.getName()))
                  .imageUrl(chosenItem.getSprites().getDefaultSprite())
                  .correct(true)
                  .build());
          alternatives.addAll(
              wrongItems
                  .stream()
                  .map(
                      item ->
                          AlternativeDto.builder()
                              .text(removeTrailingHyphensFromName(item.getName()))
                              .imageUrl(item.getSprites().getDefaultSprite())
                              .correct(false)
                              .build())
                  .collect(Collectors.toSet()));
        }
        break;
      default:
        throw new RuntimeException("Received unknown category.");
    }
    return questionBuilder.alternatives(alternatives).build();
  }

  private Pokemon getRandomPokemon() throws Exception {
    Long randomDexNumber = getRandomPokedexIndex();
    return pokeApiFacade.getPokemonByDexIndex(randomDexNumber);
  }

  private Set<Pokemon> getRandomPokemonExcept(Set<Long> except, Long quant) throws Exception {
    Set<Long> chosenDexIndexes = Sets.newHashSet();
    chosenDexIndexes.addAll(except);
    Set<Pokemon> chosenPokemon = Sets.newHashSet();
    Long randomDexNumber = getRandomPokedexIndex();
    for (int i = 0; i < quant; i++) {
      while (chosenDexIndexes.contains(randomDexNumber)) {
        randomDexNumber = getRandomPokedexIndex();
      }
      chosenDexIndexes.add(randomDexNumber);
      Pokemon randomPokemon = pokeApiFacade.getPokemonByDexIndex(randomDexNumber);
      chosenPokemon.add(randomPokemon);
    }
    return chosenPokemon;
  }

  private Item getRandomItem(List<Long> possibleIds) {
    while (true) {
      try {
        Collections.shuffle(possibleIds);
        Long randomItemId = possibleIds.get(0);
        possibleIds.remove(randomItemId);
        return pokeApiFacade.getItemById(randomItemId);
      } catch (Exception e) {
        // Max tries?
      }
    }
  }

  private Set<Item> getRandomItems(List<Long> possibleIds, Long quant) {
    Set<Item> chosenItems = Sets.newHashSet();
    for (int i = 0; i < quant; i++) {
      Collections.shuffle(possibleIds);
      Long randomItemId = possibleIds.get(0);
      possibleIds.remove(randomItemId);
      try {
        Item randomItem = pokeApiFacade.getItemById(randomItemId);
        chosenItems.add(randomItem);
      } catch (Exception e) {
        i--;
      }
    }
    return chosenItems;
  }

  private Long getRandomPokedexIndex() {
    return (long) RANDOM.nextInt(808 - 1) + 1;
  }

  private String removeTrailingHyphensFromName(String name) {
    return name.replaceAll("--held", "");
  }
}
