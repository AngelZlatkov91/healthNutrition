package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInCartRepositories extends JpaRepository<ProductInCart, Long> {
}
