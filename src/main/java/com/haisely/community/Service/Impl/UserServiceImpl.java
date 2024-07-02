package com.haisely.community.Service.Impl;

import com.haisely.community.DTO.User.*;
import com.haisely.community.Entity.Image;
import com.haisely.community.Entity.User;
import com.haisely.community.Exception.IncorrectUserInfoException;
import com.haisely.community.Exception.ResourceNotFoundException;
import com.haisely.community.Repository.BoardRepository;
import com.haisely.community.Repository.CommentRepository;
import com.haisely.community.Repository.ImageRepository;
import com.haisely.community.Repository.UserRepository;
import com.haisely.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ImageRepository imageRepository, BoardRepository boardRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public User login(LoginDTO dto) {
        // jwt token? 처리 필요함
        User user = userRepository.findUserByEmailAndDeletedAtIsNull(dto.email())
                .orElseThrow(()-> new ResourceNotFoundException("User with email not found"));
        if (!user.getPassword().equals(dto.password())){throw new IncorrectUserInfoException("Incorrect password");}
        return user.withPassword(null);
    }

    @Override
    public User saveUser(NewUserDTO dto) {
        // profileImage null 처리
        Image i = getProfileImage(dto.profileImage());
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
        // logout 로직 짜야함
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
        User u = userRepository.findUserByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        u = u.withNickname(dto.nickname());
        Image i = getProfileImage(dto.profileImage());
        u = u.withImage(i);
        userRepository.updateContent(u);
        return true;
    }

    @Override
    public boolean editUserPasswordById(int id, EditUserPasswordDTO dto) {
        User u = userRepository.findUserByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        u = u.withPassword(dto.password());
        userRepository.updatePassword(u);
        return true;
    }

    @Override
    public boolean deleteUserById(int id) {
        // user 지우고
        userRepository.deleteById(id);
        // board 지우고
        boardRepository.deleteBoardByUserId(id);
        // comment 지우기
        return false;
    }

    @Override
    public boolean emailIsPresent(String email) {
        return userRepository.findUserByEmailAndDeletedAtIsNull(email).isPresent();
    }

    @Override
    public boolean nicknameIsPresent(String nickname) {
        return userRepository.findUserByNicknameAndDeletedAtIsNull(nickname).isPresent();
    }

    private Image getProfileImage(String url){
        Image i;
        if (url == null){
            i = imageRepository.findById(1)
                    .orElseThrow(() -> new ResourceNotFoundException("Default image not found"));
        } else {
            i = Image.builder()
                    .fileUrl(url)
                    .build();
            i = imageRepository.save(i);
        }
        return i;
    }
}
