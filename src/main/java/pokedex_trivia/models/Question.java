package pokedex_trivia.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "trivia", name = "question")
@Data
public class Question {
  @Id
  @Column(name = "id")
  UUID id;

  @Column(name = "image_url")
  String imageUrl;

  @Column(name = "stem")
  String stem;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  Set<Alternative> alternatives = new HashSet<>();
}
