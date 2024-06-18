package healthnutrition.healthnutrition.services;
import healthnutrition.healthnutrition.models.dto.DeliveryDataDTO;
public interface DeliveryDataService {

    void addAddress(DeliveryDataDTO deliveryDataDTO, String user);
}
