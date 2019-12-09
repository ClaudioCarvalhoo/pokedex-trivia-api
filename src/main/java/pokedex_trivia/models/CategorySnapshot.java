package pokedex_trivia.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(schema = "trivia", name = "category_snapshot")
@Data
public class CategorySnapshot {
  @Id
  @Column(name = "id")
  UUID id;

  @Column(name = "name")
  String name;

  @OneToMany(mappedBy = "parentCategorySnapshot", fetch = FetchType.LAZY)
  Set<CategorySnapshot> subcategories = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_category_snapshot")
  @EqualsAndHashCode.Exclude
  CategorySnapshot parentCategorySnapshot;
}
