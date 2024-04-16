package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.event.UserRegisterEvent;
import healthnutrition.healthnutrition.models.dto.DeliveryDataDTO;

public interface DeliveryDataService {

    void addAddress(DeliveryDataDTO deliveryDataDTO);
}
