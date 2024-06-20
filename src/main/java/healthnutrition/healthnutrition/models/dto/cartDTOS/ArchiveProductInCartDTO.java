package healthnutrition.healthnutrition.models.dto.cartDTOS;
public class ArchiveProductInCartDTO {
    private String name;

    private Double singlePrice;
    private int quantity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
