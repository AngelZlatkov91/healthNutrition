package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.cartDTOS.AllOrdersDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ArchiveDTO;

public interface ArchiveShoppingCartService {
    ArchiveDTO allShoppingCarts(String user);

    AllOrdersDTO allOrdersToSend();
}
