package com.haisely.community.Repository.Impl;

import com.haisely.community.Entity.User;
import com.haisely.community.Mapper.UserJdbcMapper;
import com.haisely.community.Repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserJdbcRepository implements UserRepository {
    private final NamedParameterJdbcTemplate template;
    private final UserJdbcMapper userJdbcMapper;

    public UserJdbcRepository(DataSource dataSource, UserJdbcMapper userJdbcMapper){
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.userJdbcMapper = userJdbcMapper;
    }

    @Override
    public Optional<User> findUserByEmailAndDeletedAtIsNull(String email) {
        String sql = "SELECT * FROM users u LEFT JOIN images i ON u.image_id = i.image_id WHERE u.email = :email AND u.deleted_at IS NULL";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        try {
            User user = template.queryForObject(sql, params, userJdbcMapper.defaultUserMapper());
            return Optional.ofNullable(user);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByIdAndDeletedAtIsNull(int id) {
        String sql = "SELECT * FROM users u LEFT JOIN images i ON u.image_id = i.image_id WHERE u.user_id = :id AND u.deleted_at IS NULL";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            User user = template.queryForObject(sql, params, userJdbcMapper.defaultUserMapper());
            return Optional.ofNullable(user);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByNicknameAndDeletedAtIsNull(String nickname) {
        String sql = "SELECT * FROM users u LEFT JOIN images i ON u.image_id = i.image_id WHERE u.nickname = :nickname AND u.deleted_at IS NULL";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nickname", nickname);

        try {
            User user = template.queryForObject(sql, params, userJdbcMapper.defaultUserMapper());
            return Optional.ofNullable(user);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
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
        String sql = "UPDATE users u set u.image_id = :image.id, u.nickname = :nickname WHERE u.user_id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());
        params.addValue("image.id", user.getImage().getId());
        params.addValue("nickname", user.getNickname());
        template.update(sql, params);
    }

    @Override
    public void updatePassword(User user) {
        String sql = "UPDATE users u set u.password = :password WHERE u.user_id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());
        params.addValue("password", user.getPassword());
        template.update(sql, params);
    }

    @Override
    public void deleteById(int id) {
        String sql = "UPDATE users u set u.deleted_at = CURRENT_TIMESTAMP WHERE u.user_id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        template.update(sql, params);
    }
}
