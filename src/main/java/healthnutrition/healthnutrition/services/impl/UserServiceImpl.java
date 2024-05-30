package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.event.UserRegisterEvent;
import healthnutrition.healthnutrition.models.dto.EditUserDTO;
import healthnutrition.healthnutrition.models.dto.UserRegisterDTo;
import healthnutrition.healthnutrition.models.dto.UserUpdateDTO;
import healthnutrition.healthnutrition.models.entitys.UserEntity;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepositories userRepositories, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
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
        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(userName);
          UserUpdateDTO user = new UserUpdateDTO();
          user.setEmail(byEmail.get().getEmail());
          user.setFullName(byEmail.get().getFullName());
        return user;
    }

    @Override
    @Transactional
    public void edit(EditUserDTO editUserDTO, String userEmail) {
        Optional<UserEntity> byEmail = userRepositories.findByEmail(userEmail);

        if (!editUserDTO.getFullName().isEmpty()) {
            byEmail.get().setFullName(editUserDTO.getFullName());
        }
        if (!editUserDTO.getPhone().isEmpty()) {
            byEmail.get().setPhone(editUserDTO.getPhone());
        }
        if (!editUserDTO.getEmail().isEmpty()) {
            byEmail.get().setEmail(editUserDTO.getEmail());
        }
        this.userRepositories.save(byEmail.get());
    }

    private UserEntity map(UserRegisterDTo userRegisterDTo) {
        UserEntity user = new UserEntity();
        user.setFullName(userRegisterDTo.getFullName());
        user.setEmail(userRegisterDTo.getEmail());
        user.setPhone(userRegisterDTo.getPhone());
        user.setPassword(passwordEncoder.encode(userRegisterDTo.getPassword()));
        if (this.userRepositories.count() ==0){
            user.setRole(UserRoleEnum.ADMIN);
        } else {
            user.setRole(UserRoleEnum.USER);
        }
        return user;
    }
}
