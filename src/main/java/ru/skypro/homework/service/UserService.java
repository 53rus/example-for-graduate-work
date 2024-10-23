package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    void setPassword(NewPassword newPassword);

    UserDTO getUser();

    UpdateUser updateUser(UpdateUser updateUser);

    byte[] updateUserImage(MultipartFile image);


}
