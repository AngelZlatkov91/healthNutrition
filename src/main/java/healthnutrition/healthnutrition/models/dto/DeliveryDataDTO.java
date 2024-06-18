package healthnutrition.healthnutrition.models.dto;

import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.kafka.common.protocol.types.Field;

public class DeliveryDataDTO {
    @NotEmpty(message = "The city cannot be empty!")
    @Size(min = 2,max = 20)
    private String city;
    @NotEmpty(message = "The post code cannot be empty!")
    @Size(min = 3,max = 5)
    private String postCode;
    @NotEmpty(message = "The address cannot be empty!")
    private String address;

    @NotEmpty(message = "The deliver Firm is required")
    private String firm;
    private Double priceForDelivery;
    @NotEmpty(message = "The deliver address is required")
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

    public void add() {
        double price = 0.0;
        switch (this.deliveryAddress) {
            case "OFFICE" -> price  =  0.50;
            case "ADDRESS" -> price = 5.50;
        }
        switch (this.firm){
            case "SPEEDY" -> price = price + 2.50;
            case "EKONT" -> price = price  + 3.50;
        }
        setPriceForDelivery(price);
    }
}
