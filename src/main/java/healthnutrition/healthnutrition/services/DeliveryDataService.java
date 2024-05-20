package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.event.UserRegisterEvent;
import healthnutrition.healthnutrition.models.dto.DeliveryDataDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface DeliveryDataService {

    void addAddress(DeliveryDataDTO deliveryDataDTO, String user);
}
