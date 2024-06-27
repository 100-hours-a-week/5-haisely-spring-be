package com.haisely.community.DTO.Board;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BoardDTO {
    private int boardId;
    private String nickname;
    private String profileImage;
    private String title;
    private String content;
    private String boardImage;
    private int hit;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
}
