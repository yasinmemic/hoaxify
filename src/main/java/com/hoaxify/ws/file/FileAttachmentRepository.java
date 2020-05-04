package com.hoaxify.ws.file;

import com.hoaxify.ws.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created By Yasin Memic on May, 2020
 */
public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {

    List<FileAttachment> findByDateBeforeAndHoaxIsNull(Date date);

    List<FileAttachment> findByHoax_User(User user);
}
