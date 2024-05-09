package healthnutrition.healthnutrition.models.dto;

import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;

public class DeliveryDataDTO {
    private String city;
    private String postCode;
    private String address;
    private DeliveryFirmEnum firm;
    private Double priceForDelivery;
    private DeliveryAddress deliveryAddress;
    public DeliveryDataDTO(){}

    public DeliveryDataDTO(String city, String postCode, String address, DeliveryFirmEnum firm, Double priceForDelivery, DeliveryAddress deliveryAddress) {
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

    public DeliveryFirmEnum getFirm() {
        return firm;
    }

    public void setFirm(DeliveryFirmEnum firm) {
        this.firm = firm;
        switch (firm){
            case SPEEDY -> setPriceForDelivery(getPriceForDelivery() + 2.50);
            case EKONT -> setPriceForDelivery(getPriceForDelivery() + 3.50);
        }
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
        this.deliveryAddress = deliveryAddress;
        switch (deliveryAddress) {
            case OFFICE -> setPriceForDelivery(getPriceForDelivery() + 0.50);
            case ADDRESS -> setPriceForDelivery(getPriceForDelivery() + 5.50);
        }
    }

    public static DeliveryDataDTO empty(){
        return new DeliveryDataDTO(null,null,null, null, null, null);
    }
}
