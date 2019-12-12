package pokedex_trivia.models.database_models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
  @GeneratedValue
  UUID id;

  @Column(name = "image_url")
  String imageUrl;

  @Column(name = "stem")
  String stem;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  Set<Alternative> alternatives = new HashSet<>();
}
