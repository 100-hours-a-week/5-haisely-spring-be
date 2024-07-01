package com.haisely.community.Service;

import com.haisely.community.DTO.User.*;
import com.haisely.community.Entity.User;

public interface UserService {
    // login
    User login(LoginDTO dto);

    // sign up
    User saveUser(NewUserDTO dto);

    // log out
    User logOut(int id);

    // get user by id
    UserDTO getUserById(int id);

    // edit user info
    boolean editUserInfoById(int id, EditUserContentDTO dto);

    // edit user password
    boolean editUserPasswordById(int id, EditUserPasswordDTO dto);

    // delete user
    boolean deleteUserById(int id);

    // email check
    boolean emailIsPresent(String email);
    // nickname check
    boolean nicknameIsPresent(String nickname);
}
