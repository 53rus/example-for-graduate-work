package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.User;

@Component
public class UserMapper {
    public static User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(userDTO.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        userDTO.setImage(user.getImage());
        return userDTO;
    }

    public static User mapLoginToUser(Login login) {
        User user = new User();
        user.setEmail(login.getUsername());
        user.setPassword(login.getPassword());
        return user;
    }

    public static Login mapUserToLogin(User user) {
        Login login = new Login();
        login.setUsername(user.getEmail());
        login.setPassword(user.getPassword());
        return login;
    }

    public static User mapNewPasswordToUser(NewPassword newPassword) {
        User user = new User();
        user.setPassword(newPassword.getNewPassword());
        return user;
    }

    public static NewPassword mapUserToNewPassword(User user) {
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword(user.getPassword());
        return newPassword;
    }

    public static User mapRegisterToUser(Register userDTO) {
        User user = new User();
        user.setEmail(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static Register mapUserToRegister(User user) {
        Register userDTO = new Register();
        userDTO.setUsername(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static User mapUpdateUserToUser(UpdateUser userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        return user;
    }
}
