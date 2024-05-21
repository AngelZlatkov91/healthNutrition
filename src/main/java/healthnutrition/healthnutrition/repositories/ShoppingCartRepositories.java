package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.ShoppingCart;
import healthnutrition.healthnutrition.models.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepositories extends JpaRepository<ShoppingCart, Long> {

   List<ShoppingCart> findAllByUser(UserEntity user);

}
