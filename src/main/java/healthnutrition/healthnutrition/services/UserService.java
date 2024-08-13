package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.userDTOS.EditUserDTO;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRegisterDTo;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRoleDTO;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserUpdateDTO;

import java.util.List;

public interface UserService {
    void registerUser(UserRegisterDTo userRegisterDTo);

    UserUpdateDTO getUserData(String userName);

   List<UserRoleDTO> usersRole(String user);
    void edit(EditUserDTO editUserDTO, String userEmail);



    void setRoleAdmin(String email);

    void setRoleUser(String email);

}
