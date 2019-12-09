package pokedex_trivia.mappers;

import java.util.Comparator;
import java.util.stream.Collectors;
import pokedex_trivia.models.Room;
import pokedex_trivia.models.dtos.RoomDto;
import pokedex_trivia.models.dtos.RoomSummaryDto;
import pokedex_trivia.models.dtos.ScoreDto;

public class RoomMapper {
  public static RoomDto roomToDto(Room room) {
    return RoomDto.builder()
        .id(room.getId())
        .categories(
            room.getCategories()
                .stream()
                .map(CategoryMapper::CategorySnapshotToDto)
                .collect(Collectors.toSet()))
        .questions(
            room.getQuestions()
                .stream()
                .map(QuestionMapper::questionToDto)
                .collect(Collectors.toSet()))
        .leaderboard(
            room.getScores()
                .stream()
                .map(ScoreMapper::scoreToDto)
                .sorted(Comparator.comparing(ScoreDto::getScore).reversed())
                .collect(Collectors.toList()))
        .build();
  }

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
