package ru.skypro.homework.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Optional;

@Server
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * Устанавливает пароль для пользователя
     * @param newPassword информауия о паролях
     * @param authentication аутентификационная информация пользователя
     * @return статус выполнения
     */
    @Override
    public ResponseEntity setPassword(NewPassword newPassword,
                                      Authentication authentication) {

        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User saveUser = user.get();
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), saveUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        else {
            saveUser.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            userRepository.save(saveUser);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public UserDTO getLogUser(Authentication authentication) {

        return null;
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        return null;
    }

    @Override
    public byte[] updateUserImage(MultipartFile image) {
        return new byte[0];
    }

    @Override
    public byte[] getImage(Integer id) throws IOException {
        return new byte[0];
    }

    @Override
    public User getUser(String username) {
        return null;
    }
}
