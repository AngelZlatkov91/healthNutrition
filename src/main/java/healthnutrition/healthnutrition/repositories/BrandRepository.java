package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandProduct, Long> {

    Optional<BrandProduct> findByBrand(String brand);

}
