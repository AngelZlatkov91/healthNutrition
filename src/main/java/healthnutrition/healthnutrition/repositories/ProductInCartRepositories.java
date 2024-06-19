package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInCartRepositories extends JpaRepository<ProductInCart, Long> {

    @Query(value = "select  sum(quantity) from healthnutrition.product_in_cart join healthnutrition.shoping_cart_products scp on product_in_cart.id = scp.products_id join healthnutrition.shoping_cart sc on sc.id = scp.shopping_cart_id where date = DATE(now())",nativeQuery = true)
    String QuantitySellerProduct();
}
