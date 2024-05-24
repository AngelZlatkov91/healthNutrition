package healthnutrition.healthnutrition.models.dto;

public class OrderDTO {
    private String fullName;
    private String email;
    private String phone;

    private String address;

    private String deliver;

    private ArchiveShoppingCartDTO archiveDTO;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArchiveShoppingCartDTO getArchiveDTO() {
        return archiveDTO;
    }

    public void setArchiveDTO(ArchiveShoppingCartDTO archiveDTO) {
        this.archiveDTO = archiveDTO;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }
}
