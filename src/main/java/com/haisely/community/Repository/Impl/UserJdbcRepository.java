package com.haisely.community.Repository.Impl;

import com.haisely.community.Entity.User;
import com.haisely.community.Mapper.UserMapper;
import com.haisely.community.Repository.UserRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

// jdbc 구현은 jpa 테스트 한 다음에 함~~
@Repository
public class UserJdbcRepository implements UserRepository {
    private final NamedParameterJdbcTemplate template;
    private final UserMapper userMapper;

    public UserJdbcRepository(DataSource dataSource, UserMapper userMapper){
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findUserByEmailAndDeletedAtIsNull(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByIdAndDeletedAtIsNull(int id) {
        String sql = "SELECT * FROM users u LEFT JOIN images i ON u.image_id = i.image_id WHERE u.user_id = :id AND u.deleted_at IS NULL";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        User user = template.queryForObject(sql, params, userMapper.defaultUserMapper());
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByNicknameAndDeletedAtIsNull(String nickname) {
        return Optional.empty();
    }


    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void updateContent(User user) {

    }

    @Override
    public void updatePassword(User user) {

    }

    @Override
    public void deleteById(int id) {

    }


}
