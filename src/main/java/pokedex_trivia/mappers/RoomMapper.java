package pokedex_trivia.mappers;

import pokedex_trivia.models.Room;
import pokedex_trivia.models.dtos.RoomSummaryDto;

import java.util.stream.Collectors;

public class RoomMapper {
    public static RoomSummaryDto roomToSummaryDto(Room room){
        return RoomSummaryDto.builder()
                .id(room.getId())
                .categories(room.getCategories().stream().map(CategoryMapper::CategoryToDto).collect(Collectors.toSet()))
                .build();
    }
}
