package pokedex_trivia.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "trivia", name = "category")
@Data
public class Category {
    @Id
    @Column(name = "id")
    UUID id;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    Set<Category> subcategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category")
    @EqualsAndHashCode.Exclude
    Category parentCategory;
}
