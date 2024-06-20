package healthnutrition.healthnutrition.models.dto.userDTOS;

import healthnutrition.healthnutrition.validation.userValidation.FieldMatch;

@FieldMatch(first = "newPassword",
        second = "confirmNewPassword",
        message = "Passwords should match")
public class UserUpdateDTO {

    private String fullName;

    private String email;

    private String password;

    private String newPassword;
    private String confirmNewPassword;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
