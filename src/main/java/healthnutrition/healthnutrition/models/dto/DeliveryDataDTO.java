package healthnutrition.healthnutrition.models.dto;

import jakarta.validation.constraints.NotBlank;

public class DeliveryDataDTO {

    @NotBlank
    private String city;
    @NotBlank
    private String postCode;
    @NotBlank
    private String address;

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
}
