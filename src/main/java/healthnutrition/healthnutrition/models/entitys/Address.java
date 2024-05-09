package healthnutrition.healthnutrition.models.entitys;

import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "delivery_data")
public class Address extends BaseEntity{

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String postCode;
    @Column(nullable = false)
    private String address;
    @Column
    @Enumerated(EnumType.STRING)
    private DeliveryFirmEnum firm;

    @Column(nullable = false)
    private Double priceForDelivery;

    @Column()
    @Enumerated(EnumType.STRING)
    private DeliveryAddress deliveryAddress;




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
    }
}
