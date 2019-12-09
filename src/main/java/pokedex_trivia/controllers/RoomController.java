package pokedex_trivia.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pokedex_trivia.models.dtos.RoomSummaryDto;
import pokedex_trivia.services.RoomService;

import java.util.Set;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {
    private RoomService roomService;

    @GetMapping("/{room_id}")
    @ResponseStatus(HttpStatus.OK)
    public String getRoomById(@PathVariable("room_id") long roomId) {
        return roomService.getRoomById(roomId).getCategories().iterator().next().getName();
    }

    @GetMapping("/summary")
    @ResponseStatus(HttpStatus.OK)
    public Set<RoomSummaryDto> getAllRoomSummaries(){
        return roomService.getAllRoomSummaries();
    }
}
