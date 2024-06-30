package com.haisely.community.Service.Impl;

import com.haisely.community.DTO.User.UserDTO;
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
    public UserDTO getUserById(int id) {
        User u = userRepository.findUserByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        UserDTO dto = new UserDTO(u.getNickname(), u.getEmail(), u.getImage().getFileUrl());
        return dto;
    }
}
