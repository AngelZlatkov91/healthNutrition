package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.entitys.Address;
import healthnutrition.healthnutrition.models.entitys.UserEntity;
import healthnutrition.healthnutrition.repositories.DeliveryDataRepositories;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.DeliveryDataService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DeliveryDataServiceImpl implements DeliveryDataService {

    private final UserRepositories userRepositories;
    private final DeliveryDataRepositories deliveryDataRepositories;


    public DeliveryDataServiceImpl(UserRepositories userRepositories, DeliveryDataRepositories deliveryDataRepositories) {
        this.userRepositories = userRepositories;
        this.deliveryDataRepositories = deliveryDataRepositories;

    }

    @Override
    public void addAddress(DeliveryDataDTO deliveryDataDTO, String user) {
        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(user);
        deliveryDataDTO.add();
        Address address = new Address();
                  address.setCity(deliveryDataDTO.getCity());
                  address.setPostCode(deliveryDataDTO.getPostCode());
                  address.setAddress(deliveryDataDTO.getAddress());
                  address.setFirm(deliveryDataDTO.getFirm());
                  address.setDeliveryAddress(deliveryDataDTO.getDeliveryAddress());
                  address.setPriceForDelivery(deliveryDataDTO.getPriceForDelivery());
                  byEmail.get().setAddress(address);
                  this.deliveryDataRepositories.save(address);


    }
}
