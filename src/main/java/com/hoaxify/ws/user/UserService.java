package com.hoaxify.ws.user;

import com.hoaxify.ws.error.NotFoundException;
import com.hoaxify.ws.user.vm.UserUpdateVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@Service
public class UserService {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User save(User user) {
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public Page<User> getUsers(Pageable page, User user) {
        if (user != null) {
            return userRepository.findByUsernameNot(user.getUsername(), page);
        }
        return userRepository.findAll(page);
    }

    public User getByUsername(String username) {
        User inDb = userRepository.findByUsername(username);
        if (inDb == null) {
            throw new NotFoundException();
        }
        return inDb;
    }

    public User updateUser(String username, UserUpdateVM updatedUser) {
        User inDb = getByUsername(username);
        inDb.setDisplayName(updatedUser.getDisplayName());
        return userRepository.save(inDb);
    }
}
