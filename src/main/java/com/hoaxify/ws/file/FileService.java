package com.hoaxify.ws.file;

import com.hoaxify.ws.configuration.AppConfiguration;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Service
public class FileService {

    AppConfiguration appConfiguration;
    Tika tika;

    public FileService(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
        this.tika = new Tika();
    }

    public String writeBase64EncodedStringToFile(String imageName) throws IOException {
        String fileName = generateRandomName();
        File target = new File(appConfiguration.getUploadPath() + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);
        byte[] base64Encoded = Base64.getDecoder().decode(imageName);
        outputStream.write(base64Encoded);
        outputStream.close();
        return fileName;
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleteFile(String oldImageName) throws IOException {
        if (oldImageName == null) {
            return;
        }
        Files.deleteIfExists(Paths.get(appConfiguration.getUploadPath(), oldImageName));
    }

    public String detectType(String value) {
        byte[] base64Encoded = Base64.getDecoder().decode(value);
        String fileType = tika.detect(base64Encoded);
        return fileType;
    }
}
