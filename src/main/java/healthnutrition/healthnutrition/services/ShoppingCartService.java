package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.cartDTOS.AllOrdersDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ArchiveDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ShoppingCartDTO;

import java.util.UUID;


public interface ShoppingCartService {

    void addProductToShoppingCart(UUID uuid);



    Double calculateTotalPrice();

    UUID finalStep(String user);

    ShoppingCartDTO productInCart();

    void remove(String getName);

    void decrease(String getName);

    void increase(String getName);

    ArchiveDTO allShoppingCarts(String user);

    AllOrdersDTO allOrdersToSend();
    Long allShoppingCart();




}
