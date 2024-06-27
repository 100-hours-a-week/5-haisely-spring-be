//package com.haisely.community.Util;
//
//import com.haisely.community.DTO.Board.BoardDTO;
//import com.haisely.community.DTO.Comment.CommentDTO;
//import com.haisely.community.Entity.Board;
//import com.haisely.community.Entity.Comment;
//
//public class DTOConverter {
//
//    public static BoardDTO toBoardDTO(Board board) {
//        BoardDTO dto = new BoardDTO();
//        dto.setBoardId(board.getId());
//        dto.setNickname(board.getUser().getNickname());
//        dto.setProfileImage(board.getUser().getImage().getFileUrl());
//        dto.setTitle(board.getTitle());
//        dto.setContent(board.getContent());
//        if (board.getImage() != null) dto.setBoardImage(board.getImage().getFileUrl());
//        dto.setHit(board.getBoardHit().getHit());
//        dto.setCreatedAt(board.getCreatedAt());
//        dto.setUpdatedAt(board.getUpdatedAt());
//        dto.setDeletedAt(board.getDeletedAt());
//        return dto;
//    }
//
//    public static CommentDTO toCommentDTO(Comment comment) {
//        CommentDTO dto = new CommentDTO();
//        dto.setCommentId(comment.getId());
//        dto.setUserId(comment.getUser().getId());
//        dto.setNickname(comment.getUser().getNickname());
//        dto.setProfileImage(comment.getUser().getImage().getFileUrl());
//        dto.setContent(comment.getContent());
//        dto.setCreatedAt(comment.getCreatedAt());
//        return dto;
//    }
//}
