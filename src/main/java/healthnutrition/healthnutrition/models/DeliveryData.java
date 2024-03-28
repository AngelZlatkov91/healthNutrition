package healthnutrition.healthnutrition.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_data")
public class DeliveryData extends BaseEntity{

    private String city;
    private String postCode;
    private String address;




}
