package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.DeliveryFirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryFirmRepositories extends JpaRepository<DeliveryFirm, Long> {




}
