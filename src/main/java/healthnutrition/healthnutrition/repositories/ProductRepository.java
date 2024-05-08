package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.services.impl.ProductServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByName(String value);
    List<Product> findAllByBrant(BrandProduct brant);
    List<Product> findAllByType(TypeProduct type);




    Product findByUuid(UUID uuid);

//    List<Product> findAllByName(String name);
//    List<Product> findAllByBrant(String brant);
//    List<Product> findAllByType(String type);




}
