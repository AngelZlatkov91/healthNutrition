package healthnutrition.healthnutrition.models.dto.productDTOS;
public class ProductDetailsDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String type;
    private String brant;


    public ProductDetailsDTO(){
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrant() {
        return brant;
    }

    public void setBrant(String brant) {
        this.brant = brant;
    }

}
