package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ShoppingCartDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface ShoppingCartService {
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO, UserDetails user);
}
