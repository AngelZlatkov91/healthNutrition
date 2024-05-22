package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ArchiveDTO;
import healthnutrition.healthnutrition.models.dto.ArchiveShoppingCartDTO;
import healthnutrition.healthnutrition.models.dto.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.ShoppingCartDTO;

import java.util.List;
import java.util.UUID;


public interface ShoppingCartService {

    void addProductToShoppingCart(UUID uuid);



    double calculateTotalPrice();

    UUID finalStep(String user);

    ShoppingCartDTO productInCart();

    void remove(String getName);

    void decrease(String getName);

    void increase(String getName);

    ArchiveDTO allShoppingCarts(String user);
}
