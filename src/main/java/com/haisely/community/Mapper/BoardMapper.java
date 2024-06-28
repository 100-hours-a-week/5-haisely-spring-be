package com.haisely.community.Mapper;

import com.haisely.community.DTO.Board.BoardIdDTO;
import com.haisely.community.DTO.Board.NewBoardReqDTO;
import com.haisely.community.Entity.Board;
import com.haisely.community.DTO.Board.BoardDTO;
import com.haisely.community.DTO.Comment.CommentDTO;
import com.haisely.community.Entity.Comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Mapping(source = "id", target = "boardId")
    @Mapping(source = "user.nickname", target = "nickname")
    @Mapping(source = "user.image.fileUrl", target = "profileImage")
    @Mapping(source = "boardHit.hit", target = "hit")
    @Mapping(source = "image.fileUrl", target = "boardImage")
    BoardDTO toBoardDTO(Board board);

    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.nickname", target = "nickname")
    @Mapping(source = "user.image.fileUrl", target = "profileImage")
    CommentDTO toCommentDTO(Comment comment);

    @Mapping(source = "id", target = "boardId")
    BoardIdDTO toBoardIdDTO(Board board);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "boardHit", ignore = true)
    @Mapping(target = "image", ignore = true)
    Board toBoard(NewBoardReqDTO newBoardReqDTO);
}
