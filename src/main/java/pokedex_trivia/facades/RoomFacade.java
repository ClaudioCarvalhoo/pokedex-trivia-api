package pokedex_trivia.facades;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex_trivia.models.Room;
import pokedex_trivia.repositories.RoomRepository;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomFacade {
    private RoomRepository roomRepository;

    public Room getRoomById(Long id){
        return roomRepository.findById(id).get();
    }
}
