package com.haisely.community.Repository.Impl;

import com.haisely.community.Entity.User;
import com.haisely.community.Mapper.UserMapper;
import com.haisely.community.Repository.UserRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Map;
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
        String sql = "SELECT * FROM users u LEFT JOIN images i ON u.image_id = i.image_id WHERE u.email = :email AND u.deleted_at IS NULL";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        User user = template.queryForObject(sql, params, userMapper.defaultUserMapper());
        return Optional.ofNullable(user);
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
        String sql = "SELECT * FROM users u LEFT JOIN images i ON u.image_id = i.image_id WHERE u.nickname = :nickname AND u.deleted_at IS NULL";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nickname", nickname);

        User user = template.queryForObject(sql, params, userMapper.defaultUserMapper());
        return Optional.ofNullable(user);
    }


    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (image_id, nickname, email, password, created_at, updated_at)" +
                "values(:image.id, :nickname, :email, :password, :createdAt, :updatedAt ) ";
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);

        User savedUser = new User(
                keyHolder.getKey().intValue(),
                user.getNickname(),
                user.getEmail(),
                user.getPassword(),
                user.getImage(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt());
        return savedUser;
    }

    @Override
    public void updateContent(User user) {

    }

    @Override
    public void updatePassword(User user) {

    }

    @Override
    public void deleteById(int id) {
        String sql = "UPDATE users u set u.deleted_at = CURRENT_TIMESTAMP WHERE u.user_id = ?";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        template.update(sql, params);
    }
}
