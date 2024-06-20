package healthnutrition.healthnutrition.models.dto.userDTOS;

import healthnutrition.healthnutrition.validation.userValidation.UniqueUserEmail;
import healthnutrition.healthnutrition.validation.userValidation.UniqueUserPhone;
import jakarta.validation.constraints.Email;


public class EditUserDTO {
    private String fullName;
    @Email
    @UniqueUserEmail
    private String email;
    @UniqueUserPhone
    private String phone;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
