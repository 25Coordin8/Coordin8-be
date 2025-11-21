package com.example.coordin8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "avatar")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvatarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", length = 500)
    private String imageUrl; // 이미지 파일 URL (예: /images/avatar/xxx.png 또는 S3 URL)

    // Lombok이 제대로 작동하지 않을 경우를 대비한 명시적 getter/setter
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
