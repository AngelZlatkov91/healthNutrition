package healthnutrition.healthnutrition.models.dto.cartDTOS;

import java.util.ArrayList;
import java.util.List;

public class AllOrdersDTO {

    private List<OrderDTO> orders;

    public AllOrdersDTO() {
        this.orders = new ArrayList<>();
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
