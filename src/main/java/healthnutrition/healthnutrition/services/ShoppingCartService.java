package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.ShoppingCartDTO;

import java.util.List;
import java.util.UUID;


public interface ShoppingCartService {

    void addProductToShoppingCart(UUID uuid);

    void removeQuantityToProduct(UUID uuid, int quantity);

    double calculateTotalPrice();

    UUID finalStep();

    ShoppingCartDTO productInCart();

}
