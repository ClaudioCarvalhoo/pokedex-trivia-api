package pokedex_trivia.mappers;

import java.util.stream.Collectors;
import pokedex_trivia.models.Question;
import pokedex_trivia.models.dtos.QuestionDto;

public class QuestionMapper {
  public static QuestionDto questionToDto(Question question) {
    return QuestionDto.builder()
        .id(question.getId())
        .stem(question.getStem())
        .imageUrl(question.getImageUrl())
        .alternatives(
            question
                .getAlternatives()
                .stream()
                .map(AlternativeMapper::alternativeToDto)
                .collect(Collectors.toSet()))
        .build();
  }
}
