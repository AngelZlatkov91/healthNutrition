package healthnutrition.healthnutrition.models.dto;

import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class DeliveryFirmDTO {


    private DeliveryFirmEnum name;


    private Double priceForDelivery;


    private DeliveryAddress deliveryAddress;

   public DeliveryFirmDTO(){}

    public DeliveryFirmEnum getName() {
        return name;
    }

    public void setName(DeliveryFirmEnum name) {
       switch (name) {
           case EKONT -> setPriceForDelivery(getPriceForDelivery() + 3.50);
           case SPEEDY -> setPriceForDelivery(getPriceForDelivery() + 2.50);
       }
        this.name = name;
    }

    public Double getPriceForDelivery() {
        return priceForDelivery;
    }

    public void setPriceForDelivery(Double priceForDelivery) {
        this.priceForDelivery = priceForDelivery;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (Objects.requireNonNull(deliveryAddress) == DeliveryAddress.ADDRESS) {
            setPriceForDelivery(getPriceForDelivery() + 3.90);
        }
        this.deliveryAddress = deliveryAddress;
    }
}
