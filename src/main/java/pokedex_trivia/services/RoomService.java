package pokedex_trivia.services;

import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex_trivia.facades.RoomFacade;
import pokedex_trivia.models.dtos.RoomDto;
import pokedex_trivia.models.dtos.RoomSummaryDto;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {
  private RoomFacade roomFacade;

  public RoomDto getRoomById(Long id) {
    return roomFacade.getRoomById(id);
  }

  public Set<RoomSummaryDto> getAllRoomSummaries() {
    return roomFacade.getAllRoomSummaries();
  }
}
