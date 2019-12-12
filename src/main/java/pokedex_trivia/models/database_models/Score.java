package pokedex_trivia.models.database_models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;

@Entity
@Table(schema = "trivia", name = "score")
@Data
public class Score {
  @EmbeddedId @EqualsAndHashCode.Include Id id;

  @Column(name = "score")
  Long score;

  @Embeddable
  @Value
  @AllArgsConstructor
  @NoArgsConstructor(force = true)
  public static class Id implements Serializable {
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "username")
    private String username;
  }
}
