package healthnutrition.healthnutrition.models.entitys;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(nullable = false,unique = true)
    @Email
    private String email;
    @Column(nullable = false)
    @Size(min = 3,max = 20)
    private String fullName;

    @Column(nullable = false,unique = true)
    @Size(min = 9)
    private String phone;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Address address;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;


    public UserEntity() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
