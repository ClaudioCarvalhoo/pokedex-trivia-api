package pokedex_trivia.facades;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex_trivia.mappers.RoomMapper;
import pokedex_trivia.models.Room;
import pokedex_trivia.models.dtos.RoomSummaryDto;
import pokedex_trivia.repositories.RoomRepository;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomFacade {
  private RoomRepository roomRepository;

  public Room getRoomById(Long id) {
    return roomRepository.findById(id).get();
  }

  public Set<RoomSummaryDto> getAllRoomSummaries() {
    return roomRepository
        .findAll()
        .stream()
        .map(RoomMapper::roomToSummaryDto)
        .collect(Collectors.toSet());
  }
}
