package com.example.coordin8.dto.userDto;

import com.example.coordin8.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String userName;
    private String userMajor;
    private String bio;
    private Long avatarId;

    public UserResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.userName = userEntity.getUserName();
        this.userMajor = userEntity.getUserMajor();
        this.bio = userEntity.getBio();
        this.avatarId = userEntity.getAvatar() != null ? userEntity.getAvatar().getId() : null;
    }
}
