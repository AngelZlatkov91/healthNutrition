package healthnutrition.healthnutrition.repositories;
import healthnutrition.healthnutrition.models.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories  extends JpaRepository<UserEntity,Long> {

     Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhone(String value);


}
