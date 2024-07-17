package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArticlesRepositories extends JpaRepository<Articles, Long> {

    // find article by title
    Optional<Articles> findByTitle(String title);

    // get article by uuid
    Articles findByUuid(UUID uuid);

}
