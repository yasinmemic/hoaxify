package com.hoaxify.ws.hoax;

import com.hoaxify.ws.file.FileAttachment;
import com.hoaxify.ws.file.FileAttachmentRepository;
import com.hoaxify.ws.hoax.vm.HoaxSubmitVM;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Service
public class HoaxService {

    HoaxRepository hoaxRepository;

    UserService userService;

    FileAttachmentRepository fileAttachmentRepository;

    public HoaxService(HoaxRepository hoaxRepository, UserService userService, FileAttachmentRepository fileAttachmentRepository) {
        this.hoaxRepository = hoaxRepository;
        this.userService = userService;
        this.fileAttachmentRepository = fileAttachmentRepository;
    }

    public void save(HoaxSubmitVM hoaxSubmitVM, User user) {
        Hoax hoax = new Hoax();
        hoax.setContent(hoaxSubmitVM.getContent());
        hoax.setTimestamp(new Date());
        hoax.setUser(user);
        hoaxRepository.save(hoax);

        Optional<FileAttachment> fileAttachment = fileAttachmentRepository.findById(hoaxSubmitVM.getAttachmentId());
        if (fileAttachment.isPresent()) {
            FileAttachment attachment = fileAttachment.get();
            attachment.setHoax(hoax);
            fileAttachmentRepository.save(attachment);
        }
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
