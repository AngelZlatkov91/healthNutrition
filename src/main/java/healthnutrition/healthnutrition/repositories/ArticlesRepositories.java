package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticlesRepositories extends JpaRepository<Articles, Long> {


    Optional<Articles> findByTitle(String title);


}
