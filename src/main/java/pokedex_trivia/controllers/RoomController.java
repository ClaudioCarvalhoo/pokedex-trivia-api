package pokedex_trivia.controllers;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pokedex_trivia.models.dtos.RoomDto;
import pokedex_trivia.models.dtos.RoomSummaryDto;
import pokedex_trivia.models.requests.PostAnswerRequest;
import pokedex_trivia.models.requests.PostRoomRequest;
import pokedex_trivia.services.RoomService;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {
  private RoomService roomService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long createRoom(@RequestBody @Valid PostRoomRequest request) {
    return roomService.createRoom(request);
  }

  @GetMapping("/{room_id}")
  @ResponseStatus(HttpStatus.OK)
  public RoomDto getRoomById(@PathVariable("room_id") Long roomId) {
    return roomService.getRoomById(roomId);
  }

  @GetMapping("/summary")
  @ResponseStatus(HttpStatus.OK)
  public Set<RoomSummaryDto> getAllRoomSummaries() {
    return roomService.getAllRoomSummaries();
  }

  @PostMapping("/{room_id}/answer")
  @ResponseStatus(HttpStatus.OK)
  public void processAnswer(
      @PathVariable("room_id") Long roomId, @RequestBody @Valid PostAnswerRequest request) {
    roomService.calculateScoreAndSave(
        roomId,
        request.getUsername(),
        request
            .getAnswers()
            .stream()
            .map(PostAnswerRequest.Choice::getChoice)
            .collect(Collectors.toSet()));
  }
}
