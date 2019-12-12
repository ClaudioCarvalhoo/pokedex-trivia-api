package pokedex_trivia.repositories;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokedex_trivia.models.database_models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Set<Category> findAllByParentCategoryId(UUID parentCategoryId);
}
