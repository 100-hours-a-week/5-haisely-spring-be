package com.haisely.community.Repository;

import com.haisely.community.Entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(int id);

    Optional<User> findUserByNickname(String nickname);
}
