package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.StatisticForSellerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StatisticRepositories extends JpaRepository<StatisticForSellerProduct , Long> {
}
