package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepositories extends JpaRepository<ShoppingCart, Long> {



}
