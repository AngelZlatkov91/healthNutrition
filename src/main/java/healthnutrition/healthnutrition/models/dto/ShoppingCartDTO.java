package healthnutrition.healthnutrition.models.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartDTO {
    private static ShoppingCartDTO INSTANCE;

    public static ShoppingCartDTO getINSTANCE(){
        if (INSTANCE == null) {
            INSTANCE = new ShoppingCartDTO();
        }
        return INSTANCE;
    }

    private Map<String, ProductInCartDTO> products;

    public ShoppingCartDTO (){
        this.products = new HashMap<>();
    }


    public Map<String, ProductInCartDTO> getProducts() {
        return products;
    }

    public List<ProductInCartDTO> getProductFromShoppingCart() {
        return this.getProducts().values().stream().toList();
    }

    public void empty(){
        this.products = new HashMap<>();
    }



}
