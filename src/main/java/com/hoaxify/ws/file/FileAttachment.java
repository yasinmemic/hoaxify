package com.hoaxify.ws.file;

import com.hoaxify.ws.hoax.Hoax;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created By Yasin Memic on May, 2020
 */
@Entity
@Data
@ApiModel(value = "Model for files")
public class FileAttachment {

    @ApiModelProperty(required = true, value = "File ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(required = true, value = "File Name")
    private String name;

    @ApiModelProperty(required = true, value = "File Created Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ApiModelProperty(required = true, value = "File's Hoaxes")
    @OneToOne
    private Hoax hoax;

    @ApiModelProperty(required = true, value = "File Type")
    private String fileType;
}