package com.haisely.community.Repository;

import com.haisely.community.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(int id);

    Optional<User> findUserByNickname(String nickname);
}
