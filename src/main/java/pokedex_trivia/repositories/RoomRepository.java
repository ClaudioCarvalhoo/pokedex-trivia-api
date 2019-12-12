package pokedex_trivia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokedex_trivia.models.database_models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {}
