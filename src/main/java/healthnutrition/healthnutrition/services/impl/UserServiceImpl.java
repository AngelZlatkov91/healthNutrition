package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.Exception.DatabaseException;
import healthnutrition.healthnutrition.event.UserRegisterEvent;
import healthnutrition.healthnutrition.models.dto.userDTOS.EditUserDTO;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRegisterDTo;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRoleDTO;
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
import java.util.List;
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
    // register user with private method map
    public void registerUser(UserRegisterDTo userRegisterDTo) {
        try {
            this.userRepositories.save(map(userRegisterDTo));
            applicationEventPublisher.publishEvent(new UserRegisterEvent("UserService",
                    userRegisterDTo.getEmail(), userRegisterDTo.getFullName()));

        } catch (Exception ex) {
            throw  new DatabaseException("Error something is wrong");
        }

    }

    @Override
    // get user data from currency login user
    public UserUpdateDTO getUserData(String userName) {
        Optional<User> byEmail = this.userRepositories.findByEmail(userName);
        return this.mapper.map(byEmail.get(),UserUpdateDTO.class);
    }

    @Override
    public List<UserRoleDTO> usersRole(String user) {
      return userRepositories.findAllByEmailNotLike(user).stream().map(this::mapRole).toList();
    }
    private UserRoleDTO mapRole(User user) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setEmail(user.getEmail());
        userRoleDTO.setRole(user.getRole().toString());
        return userRoleDTO;
    }

    @Override
    @Transactional
    // edit currency user data transactional
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

    // set role to admin
    @Override
    public void setRoleAdmin(String email) {
        Optional<User> byEmail = userRepositories.findByEmail(email);
        byEmail.get().setRole(UserRoleEnum.ADMIN);
        userRepositories.saveAndFlush(byEmail.get());

    }
     // set role to user
    @Override
    public void setRoleUser(String email) {
        Optional<User> byEmail = userRepositories.findByEmail(email);
        byEmail.get().setRole(UserRoleEnum.USER);
        userRepositories.saveAndFlush(byEmail.get());
    }




    private User map(UserRegisterDTo userRegisterDTo) {
        User user = this.mapper.map(userRegisterDTo, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTo.getPassword()));
        // init role for user
        if (this.userRepositories.count() ==0){
            user.setRole(UserRoleEnum.ADMIN);
        } else {
            user.setRole(UserRoleEnum.USER);
        }
        return user;
    }


}
