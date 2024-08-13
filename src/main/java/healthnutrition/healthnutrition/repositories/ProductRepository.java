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


    // find product by name - name is unique
    Optional<Product> findByName(String value);

    // find product by uuid
    Product findByUuid(UUID uuid);
  // for search bar
    List<Product> findByNameContainingIgnoreCaseOrBrantBrandContainingIgnoreCaseOrTypeTypeContainingIgnoreCase(
            String key1, String key2,String key3, Pageable pageable
    );
     // delete product with uuid from admin
    void deleteProductByName(String name);








}
