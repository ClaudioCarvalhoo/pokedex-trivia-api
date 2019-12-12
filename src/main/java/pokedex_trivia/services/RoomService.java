package pokedex_trivia.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex_trivia.facades.AlternativeFacade;
import pokedex_trivia.facades.RoomFacade;
import pokedex_trivia.facades.ScoreFacade;
import pokedex_trivia.models.dtos.QuestionDto;
import pokedex_trivia.models.dtos.RoomDto;
import pokedex_trivia.models.dtos.RoomSummaryDto;
import pokedex_trivia.models.dtos.ScoreDto;
import pokedex_trivia.models.requests.PostRoomRequest;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {
  private QuestionService questionService;
  private RoomFacade roomFacade;
  private AlternativeFacade alternativeFacade;
  private ScoreFacade scoreFacade;

  public Long createRoom(PostRoomRequest request) {
    List<String> categories = Lists.newArrayList(request.getCategories());
    Set<QuestionDto> questions = Sets.newHashSet();
    for (int i = 0; i < request.getNumberOfQuestions(); i++) {
      Collections.shuffle(categories);
      String randomCategoryId = categories.get(0);
      try {
        questions.add(questionService.createQuestion(randomCategoryId));
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    }
    RoomDto room =
        RoomDto.builder().categories(Sets.newHashSet(categories)).questions(questions).build();
    return roomFacade.createRoom(room);
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
