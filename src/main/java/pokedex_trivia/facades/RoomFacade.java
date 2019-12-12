package pokedex_trivia.facades;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pokedex_trivia.mappers.AlternativeMapper;
import pokedex_trivia.mappers.QuestionMapper;
import pokedex_trivia.mappers.RoomMapper;
import pokedex_trivia.models.dtos.QuestionDto;
import pokedex_trivia.models.dtos.RoomDto;
import pokedex_trivia.models.dtos.RoomSummaryDto;
import pokedex_trivia.repositories.AlternativeRepository;
import pokedex_trivia.repositories.QuestionRepository;
import pokedex_trivia.repositories.RoomRepository;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomFacade {
  private RoomRepository roomRepository;
  private QuestionRepository questionRepository;
  private AlternativeRepository alternativeRepository;

  @Transactional
  public Long createRoom(RoomDto room) {
    for (QuestionDto question : room.getQuestions()) {
      questionRepository.save(QuestionMapper.dtoToQuestion(question));
      alternativeRepository.saveAll(
          question
              .getAlternatives()
              .stream()
              .map(alternative -> AlternativeMapper.dtoToAlternative(alternative, question.getId()))
              .collect(Collectors.toSet()));
    }
    return roomRepository.save(RoomMapper.dtoToRoom(room)).getId();
  }

  public RoomDto getRoomById(Long id) {
    return RoomMapper.roomToDto(roomRepository.findById(id).get());
  }

  public Set<RoomSummaryDto> getAllRoomSummaries() {
    return roomRepository
        .findAll()
        .stream()
        .map(RoomMapper::roomToSummaryDto)
        .collect(Collectors.toSet());
  }
}
