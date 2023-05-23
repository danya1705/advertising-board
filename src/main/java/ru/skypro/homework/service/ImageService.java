package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${path.to.image.folder}")
    private String imageDir;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Загрузка изображения
     *
     * @param imageFile файл изображения
     * @return Image
     */
    public Image uploadImage(MultipartFile imageFile) throws IOException {

        Image image = new Image();
        Path filePath = Path.of(imageDir, imageFile.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        Files.write(filePath, imageFile.getBytes());

        image.setFilePath(filePath.toString());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());

        return imageRepository.save(image);
    }
}
