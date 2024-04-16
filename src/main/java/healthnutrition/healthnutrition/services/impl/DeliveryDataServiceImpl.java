//package healthnutrition.healthnutrition.services.impl;
//
//import healthnutrition.healthnutrition.event.UserRegisterEvent;
//import healthnutrition.healthnutrition.models.dto.DeliveryDataDTO;
//import healthnutrition.healthnutrition.models.entitys.Address;
//import healthnutrition.healthnutrition.models.entitys.UserEntity;
//import healthnutrition.healthnutrition.repositories.DeliveryDataRepositories;
//import healthnutrition.healthnutrition.repositories.UserRepositories;
//import healthnutrition.healthnutrition.services.DeliveryDataService;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//import java.util.Optional;
//
//@Service
//public class DeliveryDataServiceImpl implements DeliveryDataService {
//
//    private final UserRepositories userRepositories;
//    private final DeliveryDataRepositories deliveryDataRepositories;
//    private final UserRegisterEvent user;
//
//    public DeliveryDataServiceImpl(UserRepositories userRepositories, DeliveryDataRepositories deliveryDataRepositories, UserRegisterEvent user) {
//        this.userRepositories = userRepositories;
//        this.deliveryDataRepositories = deliveryDataRepositories;
//        this.user = user;
//    }
//
//    @Override
//    @Transactional
//    public void addAddress(DeliveryDataDTO deliveryDataDTO) {
//        String userEmail = this.user.getUserEmail();
//        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(userEmail);
//        Address address = new Address();
//        Optional<Address> byCityAndPostCode = this.deliveryDataRepositories.findByCityAndPostCode(deliveryDataDTO.getCity(), deliveryDataDTO.getPostCode());
//
//        if (byCityAndPostCode.isPresent()) {
//            address = byCityAndPostCode.get();
//        } else {
//            address.setCity(deliveryDataDTO.getCity());
//            address.setPostCode(deliveryDataDTO.getPostCode());
//            address.setAddress(deliveryDataDTO.getAddress());
//        }
//        this.deliveryDataRepositories.save(address);
//
//        byEmail.get().setAddress(address);
//
//    }
//}
