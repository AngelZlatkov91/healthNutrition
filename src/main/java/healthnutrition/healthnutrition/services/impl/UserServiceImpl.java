package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.event.UserRegisterEvent;
import healthnutrition.healthnutrition.models.dto.userDTOS.EditUserDTO;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRegisterDTo;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserUpdateDTO;
import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepositories userRepositories, PasswordEncoder passwordEncoder, ModelMapper mapper, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void registerUser(UserRegisterDTo userRegisterDTo) {
        this.userRepositories.save(map(userRegisterDTo));
        applicationEventPublisher.publishEvent(new UserRegisterEvent("UserService",
                userRegisterDTo.getEmail(), userRegisterDTo.getFullName()));
    }

    @Override
    public UserUpdateDTO getUserData(String userName) {
        Optional<User> byEmail = this.userRepositories.findByEmail(userName);
          UserUpdateDTO user = new UserUpdateDTO();
          user.setEmail(byEmail.get().getEmail());
          user.setFullName(byEmail.get().getFullName());
        return user;
    }

    @Override
    @Transactional
    public void edit(EditUserDTO editUserDTO, String userEmail) {
        Optional<User> byEmail = userRepositories.findByEmail(userEmail);
        boolean isEdit = false;

        if (!editUserDTO.getFullName().isEmpty()) {
            byEmail.get().setFullName(editUserDTO.getFullName());
            isEdit =true;
        }
        if (!editUserDTO.getPhone().isEmpty()) {
            byEmail.get().setPhone(editUserDTO.getPhone());
            isEdit =true;
        }
        if (!editUserDTO.getEmail().isEmpty()) {
            byEmail.get().setEmail(editUserDTO.getEmail());
            isEdit =true;
        }
      if (isEdit) {
          this.userRepositories.save(byEmail.get());
      }

    }



    private User map(UserRegisterDTo userRegisterDTo) {
        User user = this.mapper.map(userRegisterDTo, User.class);
//        user.setFullName(userRegisterDTo.getFullName());
//        user.setEmail(userRegisterDTo.getEmail());
//        user.setPhone(userRegisterDTo.getPhone());
        user.setPassword(passwordEncoder.encode(userRegisterDTo.getPassword()));
        if (this.userRepositories.count() ==0){
            user.setRole(UserRoleEnum.ADMIN);
        } else {
            user.setRole(UserRoleEnum.USER);
        }
        return user;
    }
}
