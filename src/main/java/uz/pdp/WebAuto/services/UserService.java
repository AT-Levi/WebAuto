package uz.pdp.WebAuto.services;

import uz.pdp.WebAuto.dto.AuthenticationDto;
import uz.pdp.WebAuto.entity.user.User;
import uz.pdp.WebAuto.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void isDeleted(AuthenticationDto user) {

    }
}