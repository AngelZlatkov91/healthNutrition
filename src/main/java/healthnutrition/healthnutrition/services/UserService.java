package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.userDTOS.EditUserDTO;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRegisterDTo;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserUpdateDTO;

public interface UserService {
    void registerUser(UserRegisterDTo userRegisterDTo);

    UserUpdateDTO getUserData(String userName);


    void edit(EditUserDTO editUserDTO, String userEmail);


}
