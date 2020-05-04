package com.hoaxify.ws.user;

import com.hoaxify.ws.error.NotFoundException;
import com.hoaxify.ws.file.FileService;
import com.hoaxify.ws.hoax.HoaxService;
import com.hoaxify.ws.user.vm.UserUpdateVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@Service
public class UserService {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    FileService fileService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FileService fileService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.fileService = fileService;
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
        if (updatedUser.getImage() != null) {
            String oldImageName = inDb.getImage();
            String storedFileName = null;
            try {
                storedFileName = fileService.writeBase64EncodedStringToFile(updatedUser.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            inDb.setImage(storedFileName);
            fileService.deleteProfileImage(oldImageName);
        }
        return userRepository.save(inDb);
    }

    public void deleteUser(String username) {
        fileService.deleteAllStoredFilesForUser(userRepository.findByUsername(username));
        userRepository.deleteByUsername(username);

    }
}
