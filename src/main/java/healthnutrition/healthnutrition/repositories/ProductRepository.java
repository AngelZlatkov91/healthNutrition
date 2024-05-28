package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByName(String value);


    Product findByUuid(UUID uuid);

    List<Product> findByNameContainingIgnoreCaseOrBrantBrandContainingIgnoreCaseOrTypeTypeContainingIgnoreCase(
            String key1, String key2,String key3, Pageable pageable
    );







}
