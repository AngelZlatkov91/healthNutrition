package healthnutrition.healthnutrition.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Table(name ="statistic_for_seller_products")
@Entity
public class StatisticForSellerProduct extends BaseEntity {
    @Column
    private LocalDate date;
    @Column
    private Integer quantity;


    public StatisticForSellerProduct(){}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
