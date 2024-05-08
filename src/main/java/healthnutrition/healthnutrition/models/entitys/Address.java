package healthnutrition.healthnutrition.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_data")
public class Address extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String city;
    @Column(nullable = false,unique = true)
    private String postCode;
    @Column(nullable = false)
    private String address;

    @ManyToOne
    private DeliveryFirm deliveryFirm;


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


    public DeliveryFirm getDeliveryFirm() {
        return deliveryFirm;
    }

    public void setDeliveryFirm(DeliveryFirm deliveryFirm) {
        this.deliveryFirm = deliveryFirm;
    }
}
