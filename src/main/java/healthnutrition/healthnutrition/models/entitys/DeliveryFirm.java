package healthnutrition.healthnutrition.models.entitys;

import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "delivery_firm")
public class DeliveryFirm extends BaseEntity{


    @Column
    @Enumerated(EnumType.STRING)
    private DeliveryFirmEnum name;

    @Column(nullable = false)
    private Double priceForDelivery;

    @Column()
    @Enumerated(EnumType.STRING)
    private DeliveryAddress deliveryAddress;



    public DeliveryFirmEnum getName() {
        return name;
    }

    public void setName(DeliveryFirmEnum name) {
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
        this.deliveryAddress = deliveryAddress;
    }
}
