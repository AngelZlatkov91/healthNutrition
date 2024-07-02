package healthnutrition.healthnutrition.models.dto.userDTOS;
import healthnutrition.healthnutrition.validation.userValidation.FieldMatch;
import healthnutrition.healthnutrition.validation.userValidation.UniqueUserEmail;
import healthnutrition.healthnutrition.validation.userValidation.UniqueUserPhone;
import jakarta.validation.constraints.*;

@FieldMatch(first = "password",
second = "confirmPassword",
message = "Passwords should match")
public class UserRegisterDTo {
    @Email
    @NotBlank(message = "The email cannot be empty!")
    @UniqueUserEmail
    private String email;
    @Size(min = 3,max = 20)
    @NotBlank(message = "The fullName cannot be empty!")
    private String fullName;
    @NotBlank(message = "The Phone cannot be empty!")
    @Size(min = 9, max = 20)
    @UniqueUserPhone
    private String phone;
    @NotBlank(message = "The Password cannot be empty!")
    @Size(min = 4,max = 20)
    private String password;
    @NotBlank(message = "The Password cannot be empty!")
    @Size(min = 4,max = 20)
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
