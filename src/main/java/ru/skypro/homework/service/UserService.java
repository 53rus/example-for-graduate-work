package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

import java.io.IOException;

public interface UserService {
    ResponseEntity setPassword(NewPassword newPassword, Authentication authentication);

    UserDTO getLogUser(Authentication authentication);

    UpdateUser updateUser(UpdateUser updateUser, Authentication authentication);

    byte[] updateUserImage(MultipartFile image);

    byte[] getImage(Integer id) throws IOException;

    User getUser(String username);


}
