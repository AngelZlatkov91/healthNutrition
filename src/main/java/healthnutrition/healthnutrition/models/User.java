package healthnutrition.healthnutrition.models;

import freemarker.template.utility.DeepUnwrap;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private int age;

    @Column(nullable = false,unique = true)
    private String phone;
    @Column(nullable = false)
    private String password;

    @OneToOne
    private DeliveryData deliveryData;






}
