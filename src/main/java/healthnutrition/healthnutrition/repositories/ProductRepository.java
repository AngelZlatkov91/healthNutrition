package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByName(String value);

//    List<Product> findAllByName(String name);
//    List<Product> findAllByBrant(String brant);
//    List<Product> findAllByType(String type);




}
