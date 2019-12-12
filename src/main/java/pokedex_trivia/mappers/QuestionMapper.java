package pokedex_trivia.mappers;

import java.util.UUID;
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

  public static Question dtoToQuestion(QuestionDto questionDto) {
    Question question = new Question();
    question.setId(questionDto.getId() == null ? UUID.randomUUID() : questionDto.getId());
    question.setStem(questionDto.getStem());
    question.setImageUrl(questionDto.getImageUrl());
    return question;
  }
}
