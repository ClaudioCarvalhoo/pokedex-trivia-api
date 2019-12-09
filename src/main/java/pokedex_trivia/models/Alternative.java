package pokedex_trivia.models;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "trivia", name = "alternative")
@Data
public class Alternative {
  @Id
  @Column(name = "id")
  UUID id;

  @Column(name = "text")
  String text;

  @Column(name = "image_url")
  String imageUrl;

  @Column(name = "correct")
  Boolean correct;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  Question question;
}
