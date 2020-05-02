package com.hoaxify.ws.hoax;

import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        return hoaxRepository.findAllByUser_Username(userInDb.getUsername(), pageable);
    }

    public Page<Hoax> getOldHoaxes(Long id, Pageable page) {
        return hoaxRepository.findByIdLessThan(id, page);
    }

    public Page<Hoax> getAllOldHoaxesByUsername(Long id, String username, Pageable pageable) {
        User inDb = userService.getByUsername(username);
        return hoaxRepository.findAllByUser_UsernameAndIdLessThan(inDb.getUsername(), id, pageable);
    }

    public Long getNewHoaxesCount(Long id) {
        return hoaxRepository.countByIdGreaterThan(id);
    }

    public Long getNewHoaxesCountByUsername(String username, Long id) {
        User inDb = userService.getByUsername(username);
        return hoaxRepository.countByIdGreaterThanAndUser_Username(id,username);
    }

    public List<Hoax> getNewHoaxes(Long id, Sort sort) {
        return hoaxRepository.findByIdGreaterThan(id,sort);
    }

    public List<Hoax> getNewHoaxesByUsername(Long id, String username, Sort sort) {
        return hoaxRepository.findByIdGreaterThanAndUser_Username(id,username,sort);
    }
}
