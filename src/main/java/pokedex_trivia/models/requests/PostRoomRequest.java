package pokedex_trivia.models.requests;

import java.util.Set;
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
public class PostRoomRequest {
  @NotNull Long numberOfQuestions;
  @NotEmpty Set<String> categories;
}
