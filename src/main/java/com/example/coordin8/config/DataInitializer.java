package com.example.coordin8.config;

import com.example.coordin8.entity.AvatarEntity;
import com.example.coordin8.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final AvatarRepository avatarRepository;

    /**
     * 애플리케이션 시작 시 초기 아바타 데이터 자동 생성
     * 이미 데이터가 있으면 생성하지 않음
     */
    @Bean
    public ApplicationRunner initializeAvatars() {
        return args -> {
            // 이미 아바타 데이터가 있으면 스킵
            if (avatarRepository.count() > 0) {
                return;
            }

            // 9개의 아바타 초기 데이터 생성
            for (int i = 1; i <= 9; i++) {
                AvatarEntity avatar = AvatarEntity.builder()
                        .imageUrl("/images/avatar/" + i + ".png")
                        .build();
                avatarRepository.save(avatar);
            }

            System.out.println("✅ 초기 아바타 데이터 9개가 생성되었습니다.");
        };
    }
}

