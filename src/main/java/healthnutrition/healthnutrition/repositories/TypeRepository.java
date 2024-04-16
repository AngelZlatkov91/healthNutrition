package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<TypeProduct, Long> {
    Optional<TypeProduct> findByType(String type);

}
