package com.hoaxify.ws.hoax.vm;

import com.hoaxify.ws.file.vm.FileAttachmentVM;
import com.hoaxify.ws.hoax.Hoax;
import com.hoaxify.ws.user.vm.UserVM;
import lombok.Data;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Data
public class HoaxVM {

    private Long id;
    private String content;
    private long timeStamp;
    private UserVM userVM;
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
