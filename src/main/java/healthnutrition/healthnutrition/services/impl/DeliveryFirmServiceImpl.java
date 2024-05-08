package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.dto.DeliveryFirmDTO;
import healthnutrition.healthnutrition.models.entitys.Address;
import healthnutrition.healthnutrition.models.entitys.DeliveryFirm;
import healthnutrition.healthnutrition.models.entitys.UserEntity;
import healthnutrition.healthnutrition.repositories.DeliveryDataRepositories;
import healthnutrition.healthnutrition.repositories.DeliveryFirmRepositories;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.DeliveryFirmService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryFirmServiceImpl implements DeliveryFirmService {
    private final DeliveryFirmRepositories deliveryFirmRepositories;
    private final DeliveryDataRepositories deliveryDataRepositories;
    private final UserRepositories userRepositories;
    private double price;



    public DeliveryFirmServiceImpl(DeliveryFirmRepositories deliveryFirmRepositories, DeliveryDataRepositories deliveryDataRepositories, UserRepositories userRepositories) {
        this.deliveryFirmRepositories = deliveryFirmRepositories;
        this.deliveryDataRepositories = deliveryDataRepositories;
        this.userRepositories = userRepositories;
    }


    @Override
    public void addDeliverFirm(UserDetails user, DeliveryFirmDTO firm) {
        Optional<UserEntity> byEmail = userRepositories.findByEmail(user.getUsername());
        DeliveryFirm deliveryFirm = byEmail.get().getAddress().getDeliveryFirm();
        if (deliveryFirm == null) {
            deliveryFirm.setDeliveryAddress(firm.getDeliveryAddress());
            deliveryFirm.setName(firm.getName());
            deliveryFirm.setPriceForDelivery(firm.getPriceForDelivery());
            this.setPrice(firm.getPriceForDelivery());
            Address address = byEmail.get().getAddress();
            address.setDeliveryFirm(deliveryFirm);
            this.deliveryFirmRepositories.save(deliveryFirm);
        }
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
