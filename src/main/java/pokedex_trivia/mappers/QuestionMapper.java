package pokedex_trivia.mappers;

import java.util.stream.Collectors;
import pokedex_trivia.models.database_models.Question;
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
                .map(alternative -> AlternativeMapper.alternativeToDto(alternative, false))
                .collect(Collectors.toSet()))
        .build();
  }

  public static Question dtoToQuestion(QuestionDto questionDto) {
    Question question = new Question();
    question.setId(questionDto.getId());
    question.setStem(questionDto.getStem());
    question.setImageUrl(questionDto.getImageUrl());
    question.setAlternatives(
        questionDto
            .getAlternatives()
            .stream()
            .map(AlternativeMapper::dtoToAlternative)
            .collect(Collectors.toSet()));
    question.getAlternatives().forEach(alternative -> alternative.setQuestion(question));
    return question;
  }
}
