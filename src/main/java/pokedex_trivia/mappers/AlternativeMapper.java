package pokedex_trivia.mappers;

import java.util.UUID;
import pokedex_trivia.models.Alternative;
import pokedex_trivia.models.Question;
import pokedex_trivia.models.dtos.AlternativeDto;

public class AlternativeMapper {
  public static AlternativeDto alternativeToDto(Alternative alternative) {
    return AlternativeDto.builder()
        .id(alternative.getId())
        .text(alternative.getText())
        .imageUrl(alternative.getImageUrl())
        .correct(alternative.getCorrect())
        .build();
  }

  public static Alternative dtoToAlternative(AlternativeDto alternativeDto, UUID questionId) {
    Alternative alternative = new Alternative();
    alternative.setId(alternativeDto.getId() == null ? UUID.randomUUID() : alternativeDto.getId());
    alternative.setText(alternativeDto.getText());
    alternative.setImageUrl(alternativeDto.getImageUrl());
    alternative.setCorrect(alternativeDto.getCorrect());
    Question parentQuestion = new Question();
    parentQuestion.setId(questionId);
    alternative.setQuestion(parentQuestion);
    return alternative;
  }
}
