package com.haisely.community.DTO.Comment;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentDTO {
    private int commentId;
    private int userId;
    private String nickname;
    private String profileImage;
    private String content;
    private Timestamp createdAt;
}
