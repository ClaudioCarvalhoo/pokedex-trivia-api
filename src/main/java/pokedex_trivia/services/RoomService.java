package pokedex_trivia.services;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex_trivia.facades.AlternativeFacade;
import pokedex_trivia.facades.RoomFacade;
import pokedex_trivia.facades.ScoreFacade;
import pokedex_trivia.models.dtos.RoomDto;
import pokedex_trivia.models.dtos.RoomSummaryDto;
import pokedex_trivia.models.dtos.ScoreDto;
import pokedex_trivia.models.requests.PostRoomRequest;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {
  private RoomFacade roomFacade;
  private AlternativeFacade alternativeFacade;
  private ScoreFacade scoreFacade;

  public Long createRoom(PostRoomRequest request) {
    Set<String> categories = request.getCategories();
    return 1L;
  }

  public RoomDto getRoomById(Long id) {
    return roomFacade.getRoomById(id);
  }

  public Set<RoomSummaryDto> getAllRoomSummaries() {
    return roomFacade.getAllRoomSummaries();
  }

  public void calculateScoreAndSave(Long roomId, String username, Set<UUID> alternativeIds) {
    Map<UUID, Boolean> correctness = alternativeFacade.getCorrectnessOfAlternatives(alternativeIds);
    Long numberOfCorrects = correctness.values().stream().filter(x -> x).count();
    scoreFacade.addScoreToRoom(
        roomId, ScoreDto.builder().username(username).score(numberOfCorrects).build());
  }
}
