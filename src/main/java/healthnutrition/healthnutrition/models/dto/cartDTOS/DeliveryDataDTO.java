package healthnutrition.healthnutrition.models.dto.cartDTOS;

import healthnutrition.healthnutrition.validation.DeliverValidation.PostCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DeliveryDataDTO {
    @NotBlank(message = "{deliver.city.user.empty}")
    private String city;
    @NotBlank(message = "{deliver.postCode.user.empty}")
    @PostCode
    private String postCode;
    @NotBlank(message = "{deliver.street.user.empty}")
    private String address;

    @NotBlank(message = "{deliver.firm.user.empty}")
    private String firm;
    private Double priceForDelivery;
    @NotBlank(message = "{deliver.type.user.empty}")
    private String deliveryAddress;
    public DeliveryDataDTO(){}

    public DeliveryDataDTO(String city, String postCode, String address, String firm, Double priceForDelivery, String deliveryAddress) {
        this.city = city;
        this.postCode = postCode;
        this.address = address;
        this.firm = firm;
        this.priceForDelivery = priceForDelivery;
        this.deliveryAddress = deliveryAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;

    }

    public Double getPriceForDelivery() {
        return priceForDelivery;
    }

    public void setPriceForDelivery(Double priceForDelivery) {
        this.priceForDelivery = priceForDelivery;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;

    }

    public static DeliveryDataDTO empty(){
        return new DeliveryDataDTO(null,null,null, null, null, null);
    }
    /// this, calculate the price for delivery
    public void add() {
        double price = 0.0;
        switch (this.deliveryAddress) {
            case "OFFICE" -> price  = price +  0.50;
            case "ADDRESS" -> price = price + 5.50;
        }
        switch (this.firm){
            case "SPEEDY" -> price = price + 2.50;
            case "EKONT" -> price = price  + 3.50;
        }
        setPriceForDelivery(price);
    }
}
