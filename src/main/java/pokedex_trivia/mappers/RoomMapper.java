package pokedex_trivia.mappers;

import java.util.stream.Collectors;
import pokedex_trivia.models.Room;
import pokedex_trivia.models.dtos.RoomSummaryDto;

public class RoomMapper {
  public static RoomSummaryDto roomToSummaryDto(Room room) {
    return RoomSummaryDto.builder()
        .id(room.getId())
        .categories(
            room.getCategories()
                .stream()
                .map(CategoryMapper::CategorySnapshotToDto)
                .collect(Collectors.toSet()))
        .build();
  }
}
