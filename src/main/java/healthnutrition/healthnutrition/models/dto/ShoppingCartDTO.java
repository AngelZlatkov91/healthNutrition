package healthnutrition.healthnutrition.models.dto;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTO {
    private List<ProductInCartDTO> products;

    public ShoppingCartDTO() {
        this.products = new ArrayList<>();
    }

    public List<ProductInCartDTO> getProducts() {
        return products;

    }
    public void setProducts(List<ProductInCartDTO> products) {
        this.products = products;
    }

    public Double calculateSumOfAllProducts(){
        Double totalPrice = 0.0;
        for (ProductInCartDTO product : this.products) {
            totalPrice+=product.totalPrice();
        }
        return totalPrice;
    }
}
