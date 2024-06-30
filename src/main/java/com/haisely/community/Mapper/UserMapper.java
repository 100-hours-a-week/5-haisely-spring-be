package com.haisely.community.Mapper;

import com.haisely.community.Entity.Image;
import com.haisely.community.Entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public RowMapper<User> defaultUserMapper() {
        return (rs, rowNum) -> {
            // Image 객체 설정
            int imageId = rs.getInt("image_id");
            Image image = null;
            if (imageId > 0) {
                image = new Image(
                        imageId,
                        rs.getString("file_url")
                );
            }
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    image,
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at"),
                    rs.getTimestamp("deleted_at")
            );
        };
    }
}