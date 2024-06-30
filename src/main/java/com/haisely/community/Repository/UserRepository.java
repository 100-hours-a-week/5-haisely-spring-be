package com.haisely.community.Repository;

import com.haisely.community.Entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByEmailAndDeletedAtIsNull(String email);

    Optional<User> findUserByIdAndDeletedAtIsNull(int id);

    Optional<User> findUserByNicknameAndDeletedAtIsNull(String nickname);

    User save(User user);

    void updateContent(User user);

    void updatePassword(User user);

    void deleteById(int id);


}
