package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.event.UserRegisterEvent;
import healthnutrition.healthnutrition.models.dto.UserRegisterDTo;
import healthnutrition.healthnutrition.models.entitys.UserEntity;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
