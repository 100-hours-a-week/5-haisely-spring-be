package com.haisely.community.repository;

import com.haisely.community.Entity.Image;
import com.haisely.community.Repository.ImageRepository;
import com.haisely.community.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.haisely.community.Entity.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void 사용자_아이디로_조회() throws Exception{
        User u = userRepository.findUserByIdAndDeletedAtIsNull(1).get();
        assertEquals(u.getNickname(), "user1");
    }

    @Test
    public void 사용자_추가() throws Exception{
        Image i = imageRepository.findById(1).get();
        User u = User.builder()
                .email("testemail")
                .image(i)
                .nickname("test")
                .password("testpass")
                .build();
        User saved = userRepository.save(u);
        User findUser = userRepository.findUserByIdAndDeletedAtIsNull(saved.getId()).get();
        assertEquals(saved.getNickname(), findUser.getNickname());
    }

    @Test
    public void 사용자_삭제() throws Exception{
        Image i = imageRepository.findById(1).get();
        User u = User.builder()
                .email("testemail")
                .image(i)
                .nickname("test")
                .password("testpass")
                .build();
        User saved = userRepository.save(u);
        User findUser = userRepository.findUserByIdAndDeletedAtIsNull(saved.getId()).get();
        assertEquals(saved.getNickname(), findUser.getNickname());

        userRepository.deleteById(saved.getId());
        Optional<User> deletedUser = userRepository.findUserByIdAndDeletedAtIsNull(saved.getId());
        assertFalse(deletedUser.isPresent());
    }
}
