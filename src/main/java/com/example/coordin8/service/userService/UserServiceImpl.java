package com.example.coordin8.service.userService;

import com.example.coordin8.dto.userDto.UserRequestDto;
import com.example.coordin8.entity.AvatarEntity;
import com.example.coordin8.entity.UserEntity;
import com.example.coordin8.repository.AvatarRepository;
import com.example.coordin8.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AvatarRepository avatarRepository;

    public UserServiceImpl(UserRepository userRepository, AvatarRepository avatarRepository) {
        this.userRepository = userRepository;
        this.avatarRepository = avatarRepository;
    }

    // ---------------------- CREATE ----------------------
    @Override
    public UserEntity createUser(UserRequestDto dto) {

        AvatarEntity avatar = null;
        if (dto.getAvatarId() != null) {
            avatar = avatarRepository.findById(dto.getAvatarId())
                    .orElseThrow(() -> new RuntimeException("Avatar not found"));
        }

        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        user.setUserMajor(dto.getUserMajor());
        user.setBio(dto.getBio());
        user.setAvatar(avatar);

        return userRepository.save(user);
    }

    // ---------------------- UPDATE ----------------------
    @Override
    public UserEntity updateUser(Long id, UserRequestDto dto) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(dto.getUserName());
        user.setUserMajor(dto.getUserMajor());
        user.setBio(dto.getBio());

        if (dto.getAvatarId() != null) {
            AvatarEntity avatar = avatarRepository.findById(dto.getAvatarId())
                    .orElseThrow(() -> new RuntimeException("Avatar not found"));
            user.setAvatar(avatar);
        }

        return userRepository.save(user);
    }

    // ---------------------- READ ALL ----------------------
    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // ---------------------- READ ONE ----------------------
    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // ---------------------- DELETE ----------------------
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
