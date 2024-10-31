package com.bxrbasov.app.service;

import com.bxrbasov.app.util.ImageBasePath;
import jakarta.servlet.http.Part;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@NoArgsConstructor
public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    private static Path basePath = ImageBasePath.getBasePath();

    public static ImageService getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<InputStream> getImage(String imagePath) {
        Path imageFullPath = Path.of(basePath.toString(), imagePath);
        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath))
                : Optional.empty();
    }

    @SneakyThrows
    public void saveImage(Part user_image, Integer user_id) {
        String name = user_image.getSubmittedFileName();
        if(!(name.equals(""))) {
            String imagePath = ImageBasePath.getBasePath().toString() + "/images" + "/" + user_id + name.substring(name.indexOf("."));
            try(InputStream inputStream = user_image.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(imagePath)) {
                int currentByte;
                while ((currentByte = inputStream.read()) != -1) {
                    fileOutputStream.write(currentByte);
                }
            }
        }
    }
}
