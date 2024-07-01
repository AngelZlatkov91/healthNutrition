package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.ShoppingCart;
import healthnutrition.healthnutrition.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShoppingCartRepositories extends JpaRepository<ShoppingCart, Long> {

   List<ShoppingCart> findAllByUserOrderByDateDesc(User user);
   List<ShoppingCart> findAllByDate(LocalDate date);

}
