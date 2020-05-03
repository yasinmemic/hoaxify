package com.hoaxify.ws.file;

import com.hoaxify.ws.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created By Yasin Memic on May, 2020
 */
@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping(ApiPaths.HoaxCtrl.HoaxAttachmentsCtrl.CTRL)
    FileAttachment saveHoaxAttachment(MultipartFile file) throws IOException {
        return fileService.saveHoaxAttachment(file);
    }
}
