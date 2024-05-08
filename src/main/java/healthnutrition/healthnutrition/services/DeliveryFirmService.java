package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.dto.DeliveryFirmDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface DeliveryFirmService {

    void addDeliverFirm(UserDetails user, DeliveryFirmDTO firm);

   double getPrice();

}
