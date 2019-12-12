package pokedex_trivia.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokedex_trivia.models.database_models.Alternative;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, UUID> {}
