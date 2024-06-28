package com.haisely.community.Repository.Impl;

import com.haisely.community.Entity.User;
import com.haisely.community.Repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

// jdbc 구현은 jpa 테스트 한 다음에 함~~
@Repository
public class UserJdbcRepository implements UserRepository {

    private final JdbcTemplate template;

    public UserJdbcRepository(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findUserByEmailAndDeletedAtIsNull(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByIdAndDeletedAtIsNull(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByNicknameAndDeletedAtIsNull(String nickname) {
        return Optional.empty();
    }
}
