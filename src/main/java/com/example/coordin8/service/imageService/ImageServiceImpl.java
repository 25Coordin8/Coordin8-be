package com.example.coordin8.service.imageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    @Value("${server.port:8080}")
    private String serverPort;

    @Override
    public String saveImage(MultipartFile file, String subDirectory) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        // 파일 확장자 검증
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !isImageFile(originalFilename)) {
            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
        }

        // 저장 디렉토리 생성
        Path uploadPath = Paths.get(uploadDir, subDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 고유한 파일명 생성 (UUID + 원본 확장자)
        String extension = getFileExtension(originalFilename);
        String uniqueFilename = UUID.randomUUID().toString() + extension;
        Path filePath = uploadPath.resolve(uniqueFilename);

        // 파일 저장
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 접근 가능한 URL 반환 (예: /images/avatar/xxx.png)
        return "/images/" + subDirectory + "/" + uniqueFilename;
    }

    @Override
    public boolean deleteImage(String imageUrl) {
        try {
            // URL에서 파일 경로 추출 (예: /images/avatar/xxx.jpg -> uploads/avatar/xxx.jpg)
            if (imageUrl == null || !imageUrl.startsWith("/images/")) {
                return false;
            }

            String relativePath = imageUrl.replace("/images/", "");
            Path filePath = Paths.get(uploadDir, relativePath);

            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isImageFile(String filename) {
        String extension = getFileExtension(filename).toLowerCase();
        return extension.equals(".jpg") || extension.equals(".jpeg") || 
               extension.equals(".png") || extension.equals(".gif") || 
               extension.equals(".webp");
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        return lastDotIndex > 0 ? filename.substring(lastDotIndex) : "";
    }
}

