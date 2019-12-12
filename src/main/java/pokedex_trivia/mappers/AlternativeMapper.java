package pokedex_trivia.mappers;

import pokedex_trivia.models.database_models.Alternative;
import pokedex_trivia.models.dtos.AlternativeDto;

public class AlternativeMapper {
  public static AlternativeDto alternativeToDto(Alternative alternative, Boolean withCorrect) {
    return AlternativeDto.builder()
        .id(alternative.getId())
        .text(alternative.getText())
        .imageUrl(alternative.getImageUrl())
        .correct(withCorrect ? alternative.getCorrect() : null)
        .build();
  }

  public static Alternative dtoToAlternative(AlternativeDto alternativeDto) {
    Alternative alternative = new Alternative();
    alternative.setId(alternativeDto.getId());
    alternative.setText(alternativeDto.getText());
    alternative.setImageUrl(alternativeDto.getImageUrl());
    alternative.setCorrect(alternativeDto.getCorrect());
    return alternative;
  }
}
