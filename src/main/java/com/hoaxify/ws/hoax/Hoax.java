package com.hoaxify.ws.hoax;

import com.hoaxify.ws.file.FileAttachment;
import com.hoaxify.ws.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created By Yasin Memic on Apr, 2020
 */

@Data
@Entity
public class Hoax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "hoax")
    private FileAttachment fileAttachment;

}
