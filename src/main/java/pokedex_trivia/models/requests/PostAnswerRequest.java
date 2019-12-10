package pokedex_trivia.models.requests;

import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Builder
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PostAnswerRequest {
  @NotNull String username;
  @NotEmpty Set<Choice> answers;

  @Builder
  @Value
  @NoArgsConstructor(force = true)
  @AllArgsConstructor
  public static class Choice {
    @NotNull UUID questionId;
    @NotNull UUID choice;
  }
}
