package healthnutrition.healthnutrition.services;
import healthnutrition.healthnutrition.models.dto.cartDTOS.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ShoppingCartDTO;
public interface ShoppingCartService {

    void addProductToShoppingCart(String name);

    Double calculateTotalPrice();

    void finalStep(String user, DeliveryDataDTO data);

    ShoppingCartDTO productInCart();

    void remove(String getName);

    void decrease(String getName);

    void increase(String getName);









}
