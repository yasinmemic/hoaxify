package com.hoaxify.ws.file;

import com.hoaxify.ws.hoax.Hoax;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created By Yasin Memic on May, 2020
 */
@Entity
@Data
public class FileAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne
    private Hoax hoax;

    private String fileType;
}