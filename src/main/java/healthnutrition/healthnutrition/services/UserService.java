package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.UserRegisterDTo;
import healthnutrition.healthnutrition.models.dto.UserUpdateDTO;

public interface UserService {
    void registerUser(UserRegisterDTo userRegisterDTo);

    UserUpdateDTO getUserData(String userName);


}
