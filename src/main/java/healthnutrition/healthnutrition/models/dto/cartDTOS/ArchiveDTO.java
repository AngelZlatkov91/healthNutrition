package healthnutrition.healthnutrition.models.dto.cartDTOS;

import java.util.List;

public class ArchiveDTO {
    private List<ArchiveShoppingCartDTO> archiveShoppingCartDTOS;

    public List<ArchiveShoppingCartDTO> getArchiveShoppingCartDTOS() {
        return archiveShoppingCartDTOS;
    }

    public void setArchiveShoppingCartDTOS(List<ArchiveShoppingCartDTO> archiveShoppingCartDTOS) {
        this.archiveShoppingCartDTOS = archiveShoppingCartDTOS;
    }

    public int size(){
        return this.archiveShoppingCartDTOS.size();
    }
}
