package com.hoaxify.ws.file.vm;

import com.hoaxify.ws.file.FileAttachment;
import lombok.Data;

/**
 * Created By Yasin Memic on May, 2020
 */
@Data
public class FileAttachmentVM {

    private String name;
    private String fileType;

    public FileAttachmentVM(FileAttachment fileAttachment) {
        this.setName(fileAttachment.getName());
        this.fileType = fileAttachment.getFileType();
    }
}
