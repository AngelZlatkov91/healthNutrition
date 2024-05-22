package healthnutrition.healthnutrition.models.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArchiveShoppingCartDTO {

    private List<ArchiveProductInCartDTO> archive;
    private LocalDate date;
    private Double totalPrice;


    public ArchiveShoppingCartDTO () {
        this.archive = new ArrayList<>();
    }

    public List<ArchiveProductInCartDTO> getArchive() {
        return archive;
    }

    public void setArchive(List<ArchiveProductInCartDTO> archive) {
        this.archive = archive;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
