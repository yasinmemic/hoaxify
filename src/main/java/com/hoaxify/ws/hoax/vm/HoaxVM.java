package com.hoaxify.ws.hoax.vm;

import com.hoaxify.ws.file.vm.FileAttachmentVM;
import com.hoaxify.ws.hoax.Hoax;
import com.hoaxify.ws.user.vm.UserVM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Data
@ApiModel(value = "Hoax Data Transfer Object")
public class HoaxVM {

    @ApiModelProperty(required = true, value = "Hoax ID")
    private Long id;

    @ApiModelProperty(required = true, value = "Hoax Content")
    private String content;

    @ApiModelProperty(required = true, value = "Hoax Created Time")
    private long timeStamp;

    @ApiModelProperty(required = true, value = "Hoax's User")
    private UserVM userVM;

    @ApiModelProperty(required = true, value = "Hoax's files")
    private FileAttachmentVM fileAttachment;

    public HoaxVM(Hoax hoax) {
        this.setId(hoax.getId());
        this.setContent(hoax.getContent());
        this.setTimeStamp(hoax.getTimestamp().getTime());
        this.setUserVM(new UserVM(hoax.getUser()));
        if (hoax.getFileAttachment() != null) {
            this.fileAttachment = new FileAttachmentVM(hoax.getFileAttachment());
        }
    }
}
