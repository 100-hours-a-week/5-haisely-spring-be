package com.haisely.community.service;

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
}
