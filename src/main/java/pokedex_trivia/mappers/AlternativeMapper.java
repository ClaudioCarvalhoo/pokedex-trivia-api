package pokedex_trivia.mappers;

import pokedex_trivia.models.Alternative;
import pokedex_trivia.models.dtos.AlternativeDto;

public class AlternativeMapper {
  public static AlternativeDto alternativeToDto(Alternative alternative) {
    return AlternativeDto.builder()
        .id(alternative.getId())
        .text(alternative.getText())
        .imageUrl(alternative.getImageUrl())
        .build();
  }
}
