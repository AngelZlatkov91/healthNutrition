package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.cartDTOS.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.entitys.Address;
import healthnutrition.healthnutrition.models.entitys.UserEntity;
import healthnutrition.healthnutrition.repositories.DeliveryDataRepositories;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.DeliveryDataService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DeliveryDataServiceImpl implements DeliveryDataService {

    private final UserRepositories userRepositories;
    private final DeliveryDataRepositories deliveryDataRepositories;
    private  final ModelMapper mapper;


    public DeliveryDataServiceImpl(UserRepositories userRepositories, DeliveryDataRepositories deliveryDataRepositories, ModelMapper mapper) {
        this.userRepositories = userRepositories;
        this.deliveryDataRepositories = deliveryDataRepositories;

        this.mapper = mapper;
    }

    @Override
    public void addAddress(DeliveryDataDTO deliveryDataDTO, String user) {
        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(user);
        deliveryDataDTO.add();
        Address address = this.mapper.map(deliveryDataDTO, Address.class);
//                  address.setCity(deliveryDataDTO.getCity());
//                  address.setPostCode(deliveryDataDTO.getPostCode());
//                  address.setAddress(deliveryDataDTO.getAddress());
//                  address.setFirm(deliveryDataDTO.getFirm());
//                  address.setDeliveryAddress(deliveryDataDTO.getDeliveryAddress());
//                  address.setPriceForDelivery(deliveryDataDTO.getPriceForDelivery());
                  byEmail.get().setAddress(address);
                  this.deliveryDataRepositories.save(address);


    }
}
