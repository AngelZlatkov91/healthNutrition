package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepositories extends JpaRepository<Articles, Long> {




}
