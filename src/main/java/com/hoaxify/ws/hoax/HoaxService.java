package com.hoaxify.ws.hoax;

import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
        Specification<Hoax> specification = userIs(userInDb);
        return hoaxRepository.findAll(specification, pageable);
    }

    public Page<Hoax> getOldHoaxes(Long id, String username, Pageable page) {
        Specification<Hoax> specification = idLessThan(id);
        if (username != null) {
            User inDb = userService.getByUsername(username);
            specification = specification.and(userIs(inDb));
        }
        return hoaxRepository.findAll(specification, page);
    }

    public Long getNewHoaxesCount(Long id, String username) {
        Specification<Hoax> specification = idGreaterThan(id);
        if (username != null) {
            User inDb = userService.getByUsername(username);
            specification = specification.and(userIs(inDb));
        }
        return hoaxRepository.count(specification);
    }

    public List<Hoax> getNewHoaxes(Long id, String username, Sort sort) {
        Specification<Hoax> specification = idGreaterThan(id);
        if (username != null) {
            User inDb = userService.getByUsername(username);
            specification = specification.and(userIs(inDb));
        }
        return hoaxRepository.findAll(specification, sort);
    }

    Specification<Hoax> idLessThan(Long id) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get("id"), id);
        });
    }

    Specification<Hoax> idGreaterThan(Long id) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("id"), id);
        });
    }

    Specification<Hoax> userIs(User user) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("user"), user);
        });
    }
}
