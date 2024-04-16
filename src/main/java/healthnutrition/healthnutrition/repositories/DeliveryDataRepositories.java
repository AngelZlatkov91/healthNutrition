package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryDataRepositories extends JpaRepository<Address, Long> {

    Optional<Address> findByCityAndPostCode(String city, String postCode);


}
