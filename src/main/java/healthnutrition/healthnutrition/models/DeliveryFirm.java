package healthnutrition.healthnutrition.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_firm")
public class DeliveryFirm extends BaseEntity{

    private String name;

    private Double priceForDelivery;

    private String AddressOrOfice;




}
