package com.hoaxify.ws.hoax;

import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Service
public class HoaxService {

    @Autowired
    HoaxRepository hoaxRepository;

    @Autowired
    UserService userService;

    public Hoax save(Hoax hoax, User user) {
        hoax.setTimestamp(new Date());
        hoax.setUser(user);
        return hoaxRepository.save(hoax);
    }

    public Page<Hoax> getAll(Pageable page) {
        return hoaxRepository.findAll(page);
    }

    public Page<Hoax> getAllByUsername(String username, Pageable pageable) {
        User userInDb = userService.getByUsername(username);
        return hoaxRepository.findAllByUser_Username(userInDb.getUsername(),pageable);
    }
}
