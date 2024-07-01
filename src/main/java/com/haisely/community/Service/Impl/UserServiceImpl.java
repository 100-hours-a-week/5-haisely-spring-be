package com.haisely.community.Service.Impl;

import com.haisely.community.DTO.User.*;
import com.haisely.community.Entity.Image;
import com.haisely.community.Entity.User;
import com.haisely.community.Exception.ResourceNotFoundException;
import com.haisely.community.Repository.ImageRepository;
import com.haisely.community.Repository.UserRepository;
import com.haisely.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public User login(LoginDTO dto) {
        return null;
    }

    @Override
    public User saveUser(NewUserDTO dto) {
        // profileImage null 처리
        Image i;
        if (dto.profileImage() == null){
            i = imageRepository.findById(1)
                    .orElseThrow(() -> new ResourceNotFoundException("Default image not found"));
        } else {
            i = Image.builder()
                    .fileUrl(dto.profileImage())
                    .build();
            i = imageRepository.save(i);
        }
        User u = User.builder()
                .password(dto.password())
                .image(i)
                .email(dto.email())
                .nickname(dto.nickname())
                .password(dto.password())
                .build();
        return userRepository.save(u);
    }

    @Override
    public User logOut(int id) {
        return null;
    }

    @Override
    public UserDTO getUserById(int id) {
        User u = userRepository.findUserByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        UserDTO dto = new UserDTO(u.getId(), u.getNickname(), u.getEmail(), u.getImage().getFileUrl());
        return dto;
    }

    @Override
    public boolean editUserInfoById(int id, EditUserContentDTO dto) {
        return false;
    }

    @Override
    public boolean editUserPasswordById(int id, EditUserPasswordDTO dto) {
        return false;
    }

    @Override
    public boolean deleteUserById(int id) {
        return false;
    }

    @Override
    public boolean emailCheck(String email) {
        return false;
    }

    @Override
    public boolean nicknameCheck(String nickname) {
        return false;
    }


}
