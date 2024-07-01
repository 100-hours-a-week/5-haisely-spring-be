package com.haisely.community.Service.Impl;

import com.haisely.community.DTO.User.*;
import com.haisely.community.Entity.User;
import com.haisely.community.Exception.ResourceNotFoundException;
import com.haisely.community.Repository.UserRepository;
import com.haisely.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User login(LoginDTO dto) {
        return null;
    }

    @Override
    public User saveUser(NewUserDTO dto) {
        return null;
    }

    @Override
    public User logOut(int id) {
        return null;
    }

    @Override
    public UserDTO getUserById(int id) {
        User u = userRepository.findUserByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        UserDTO dto = new UserDTO(u.getNickname(), u.getEmail(), u.getImage().getFileUrl());
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
