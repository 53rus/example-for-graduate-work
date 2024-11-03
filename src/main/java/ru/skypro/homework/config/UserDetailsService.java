package ru.skypro.homework.config;

import ru.skypro.homework.exception.InCorrectPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

@Component
public class UserDetailsService implements UserDetailsManager {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRole(Role.valueOf(userDetails.getAuthorities().iterator().next().getAuthority().substring(5)));
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDetails user) {
        User updateUser = userRepository.findByEmail(user.getUsername()).get();
        updateUser.setEmail(user.getUsername());
        updateUser.setPassword(user.getPassword());
        userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(String username) {
        User deleteUser = userRepository.findByEmail(username).get();
        userRepository.delete(deleteUser);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User changePasswordUser = userRepository.findByEmail(authentication.getName()).get();

        if (!changePasswordUser.getPassword().equals(passwordEncoder.encode(oldPassword))) {
            throw new InCorrectPasswordException("In Correct old Password");
        }

        changePasswordUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(changePasswordUser);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "Not Found"));

    }
}
