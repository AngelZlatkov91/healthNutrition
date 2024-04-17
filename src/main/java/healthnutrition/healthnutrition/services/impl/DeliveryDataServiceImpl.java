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
    public void addAddress(DeliveryDataDTO deliveryDataDTO, UserDetails user) {
        String userEmail = user.getUsername();
        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(userEmail);
        Address address = new Address();
        Optional<Address> byCityAndPostCode = this.deliveryDataRepositories.findByCityAndPostCode(deliveryDataDTO.getCity(), deliveryDataDTO.getPostCode());
              if (byCityAndPostCode.isEmpty()) {
                  address.setCity(deliveryDataDTO.getCity());
                  address.setPostCode(deliveryDataDTO.getPostCode());
                  address.setAddress(deliveryDataDTO.getAddress());
                  this.deliveryDataRepositories.save(address);
                  byEmail.get().setAddress(address);
              }
    }
}
