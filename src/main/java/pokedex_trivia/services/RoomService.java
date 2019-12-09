package pokedex_trivia.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex_trivia.facades.RoomFacade;
import pokedex_trivia.models.Room;
import pokedex_trivia.models.dtos.RoomSummaryDto;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {
    private RoomFacade roomFacade;

    public Room getRoomById(Long id){
        return roomFacade.getRoomById(id);
    }

    public Set<RoomSummaryDto> getAllRoomSummaries(){
        return roomFacade.getAllRoomSummaries();
    }
}
