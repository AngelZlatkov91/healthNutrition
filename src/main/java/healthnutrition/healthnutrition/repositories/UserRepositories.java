package healthnutrition.healthnutrition.repositories;
import healthnutrition.healthnutrition.models.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositories  extends JpaRepository<User,Long> {

     Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String value);
    List<User>findAllByEmailNotLike(String email);


}
