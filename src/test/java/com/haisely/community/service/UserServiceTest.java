package com.haisely.community.service;

import com.haisely.community.DTO.User.LoginDTO;
import com.haisely.community.DTO.User.NewUserDTO;
import com.haisely.community.DTO.User.UserDTO;
import com.haisely.community.Entity.User;
import com.haisely.community.Repository.Impl.UserJdbcRepository;
import com.haisely.community.Repository.UserRepository;
import com.haisely.community.Service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserJdbcRepository userRepository;

    @Test
    public void 사용자_조회() throws Exception{
        UserDTO u = userService.getUserById(1);
        assertEquals(u.nickname(), "user1");
    }

    @Test
    public void 사용자_사진과_함께_저장() throws Exception{
        NewUserDTO dto = new NewUserDTO("test user", "test@f.f", "pass", "imageurl");
        User u = userService.saveUser(dto);

        UserDTO saved = userService.getUserById(u.getId());
        assertEquals(u.getNickname(), saved.nickname());
    }

    @Test
    public void 사용자_사진과_없이_저장() throws Exception{
        NewUserDTO dto = new NewUserDTO("test user", "test@f.f", "pass", null);
        User u = userService.saveUser(dto);

        UserDTO saved = userService.getUserById(u.getId());
        assertEquals("/images/default.png", saved.profileImage());
    }

    @Test
    public void 로그인() throws Exception{
        LoginDTO dto = new LoginDTO("user1@example.com", "password1");
        User u = userService.login(dto);

        assertEquals(u.getId(), 1);
    }
}
