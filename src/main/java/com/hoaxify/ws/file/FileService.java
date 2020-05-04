package com.hoaxify.ws.file;

import com.hoaxify.ws.configuration.AppConfiguration;
import com.hoaxify.ws.user.User;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Service
public class FileService {

    AppConfiguration appConfiguration;
    Tika tika;
    FileAttachmentRepository fileAttachmentRepository;

    public FileService(AppConfiguration appConfiguration, FileAttachmentRepository fileAttachmentRepository) {
        this.appConfiguration = appConfiguration;
        this.tika = new Tika();
        this.fileAttachmentRepository = fileAttachmentRepository;
    }

    public String writeBase64EncodedStringToFile(String imageName) throws IOException {
        String fileName = generateRandomName();
        File target = new File(appConfiguration.getProfileStoragePath() + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);
        byte[] base64Encoded = Base64.getDecoder().decode(imageName);
        outputStream.write(base64Encoded);
        outputStream.close();
        return fileName;
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleteProfileImage(String oldImageName) {
        if (oldImageName == null) {
            return;
        }
        deleteFile(Paths.get(appConfiguration.getProfileStoragePath(), oldImageName));
    }

    public String detectType(String value) {
        byte[] base64Encoded = Base64.getDecoder().decode(value);
        String fileType = tika.detect(base64Encoded);
        return fileType;
    }

    public String detectType(byte[] array) {
        String fileType = tika.detect(array);
        return fileType;
    }

    public FileAttachment saveHoaxAttachment(MultipartFile multipartFile) throws IOException {
        String fileName = generateRandomName();
        File target = new File(appConfiguration.getAttachmentStoragePath() + "/" + fileName);

        byte[] array = multipartFile.getBytes();
        OutputStream outputStream = new FileOutputStream(target);
        outputStream.write(array);
        outputStream.close();
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setName(fileName);
        fileAttachment.setDate(new Date());
        String fileType = detectType(array);
        fileAttachment.setFileType(fileType);
        return fileAttachmentRepository.save(fileAttachment);
    }

    public void deleteAttachmentFile(String oldImageName) {
        if (oldImageName == null) {
            return;
        }
        deleteFile(Paths.get(appConfiguration.getAttachmentStoragePath(), oldImageName));
    }

    private void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllStoredFilesForUser(User byUsername) {
        deleteProfileImage(byUsername.getImage());
        List<FileAttachment> filesToBeRemoved = fileAttachmentRepository.findByHoax_User(byUsername);
        filesToBeRemoved.forEach(file -> deleteAttachmentFile(file.getName()));

    }
}
