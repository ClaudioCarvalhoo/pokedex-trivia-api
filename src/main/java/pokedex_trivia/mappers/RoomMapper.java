package pokedex_trivia.mappers;

import java.util.Comparator;
import java.util.stream.Collectors;
import pokedex_trivia.models.database_models.Category;
import pokedex_trivia.models.database_models.Room;
import pokedex_trivia.models.dtos.RoomDto;
import pokedex_trivia.models.dtos.RoomSummaryDto;
import pokedex_trivia.models.dtos.ScoreDto;

public class RoomMapper {
  public static RoomDto roomToDto(Room room) {
    return RoomDto.builder()
        .id(room.getId())
        .categories(room.getCategories().stream().map(Category::getId).collect(Collectors.toSet()))
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
                .map(CategoryMapper::CategoryToDto)
                .collect(Collectors.toSet()))
        .build();
  }

  public static Room dtoToRoom(RoomDto roomDto) {
    Room room = new Room();
    room.setCategories(
        roomDto
            .getCategories()
            .stream()
            .map(
                categoryId -> {
                  Category category = new Category();
                  category.setId(categoryId);
                  return category;
                })
            .collect(Collectors.toSet()));
    room.setQuestions(
        roomDto
            .getQuestions()
            .stream()
            .map(QuestionMapper::dtoToQuestion)
            .collect(Collectors.toSet()));
    return room;
  }
}
