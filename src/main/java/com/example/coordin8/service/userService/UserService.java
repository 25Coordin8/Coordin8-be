package com.example.coordin8.service.userService;

import com.example.coordin8.dto.userDto.UserRequestDto;
import com.example.coordin8.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity createUser(UserRequestDto requestDto);
    UserEntity updateUser(Long id, UserRequestDto requestDto);
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(Long id);
    void deleteUser(Long id);
}

