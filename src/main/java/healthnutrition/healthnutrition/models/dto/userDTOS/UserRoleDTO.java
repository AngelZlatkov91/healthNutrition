package healthnutrition.healthnutrition.models.dto.userDTOS;


public class UserRoleDTO {

    private String email;
    private String role;
    public UserRoleDTO(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
