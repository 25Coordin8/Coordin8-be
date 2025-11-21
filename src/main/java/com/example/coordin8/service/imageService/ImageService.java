package com.example.coordin8.service.imageService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    /**
     * 이미지 파일을 저장하고 접근 가능한 URL을 반환합니다.
     * 
     * @param file 업로드할 이미지 파일
     * @param subDirectory 저장할 하위 디렉토리 (예: "avatar", "project")
     * @return 접근 가능한 이미지 URL (예: "/images/avatar/xxx.jpg")
     * @throws IOException 파일 저장 실패 시
     */
    String saveImage(MultipartFile file, String subDirectory) throws IOException;

    /**
     * 이미지 파일을 삭제합니다.
     * 
     * @param imageUrl 삭제할 이미지의 URL
     * @return 삭제 성공 여부
     */
    boolean deleteImage(String imageUrl);
}

