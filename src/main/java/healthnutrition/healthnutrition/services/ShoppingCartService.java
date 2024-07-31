package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.cartDTOS.AllOrdersDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ArchiveDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ShoppingCartDTO;

import java.util.UUID;


public interface ShoppingCartService {

    void addProductToShoppingCart(UUID uuid);

    Double calculateTotalPrice();

    void finalStep(String user, DeliveryDataDTO data);

    ShoppingCartDTO productInCart();

    void remove(String getName);

    void decrease(String getName);

    void increase(String getName);









}
