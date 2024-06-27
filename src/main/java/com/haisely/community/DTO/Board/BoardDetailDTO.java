package com.haisely.community.DTO.Board;

import com.haisely.community.DTO.Comment.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardDetailDTO {
    private BoardDTO board;
    private List<CommentDTO> comments;
}
