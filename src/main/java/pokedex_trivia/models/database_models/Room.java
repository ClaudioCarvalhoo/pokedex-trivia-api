package pokedex_trivia.models.database_models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(schema = "trivia", name = "room")
@Data
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
  @SequenceGenerator(
      schema = "trivia",
      name = "room_generator",
      sequenceName = "room_seq",
      allocationSize = 1)
  @EqualsAndHashCode.Include
  @Column(name = "id")
  Long id;

  @ManyToMany(cascade = {CascadeType.MERGE})
  @JoinTable(
      schema = "trivia",
      name = "room_category",
      joinColumns = @JoinColumn(name = "room_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  Set<Category> categories = new HashSet<>();

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
      schema = "trivia",
      name = "room_question",
      joinColumns = @JoinColumn(name = "room_id"),
      inverseJoinColumns = @JoinColumn(name = "question_id"))
  Set<Question> questions = new HashSet<>();

  @OneToMany(mappedBy = "id.roomId", fetch = FetchType.LAZY)
  List<Score> scores = new ArrayList<>();
}
