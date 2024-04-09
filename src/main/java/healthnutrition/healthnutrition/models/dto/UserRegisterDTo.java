package healthnutrition.healthnutrition.models.dto;
import healthnutrition.healthnutrition.validation.FieldMatch;
import healthnutrition.healthnutrition.validation.UniqueUserEmail;
import healthnutrition.healthnutrition.validation.UniqueUserPhone;
import jakarta.validation.constraints.*;

@FieldMatch(first = "password",
second = "confirmPassword",
message = "Passwords should match")
public class UserRegisterDTo {
    @Email
    @NotBlank
    @UniqueUserEmail
    private String email;
    @Size(min = 3,max = 20)
    private String fullName;
    @Positive
    @Min(12)
    @NotBlank
    private int age;
    @NotBlank
    @Size(min = 9)
    @UniqueUserPhone
    private String phone;
    @NotBlank
    @Size(min = 4,max = 20)
    private String password;
    @NotBlank
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
