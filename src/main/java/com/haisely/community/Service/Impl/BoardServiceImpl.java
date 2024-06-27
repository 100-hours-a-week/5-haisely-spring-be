package com.haisely.community.Service.Impl;

import com.haisely.community.DTO.Board.BoardDTO;
import com.haisely.community.Entity.Board;
import com.haisely.community.Repository.BoardRepository;
import com.haisely.community.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<BoardDTO> getBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(this::toBoardDTO)
                .collect(Collectors.toList());
    }


    public BoardDTO toBoardDTO(Board board){
        BoardDTO dto = new BoardDTO();
        dto.setBoardId(board.getId());
        dto.setNickname(board.getUser().getNickname());
        dto.setProfileImage(board.getUser().getImage().getFileUrl());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        if (board.getImage() != null) dto.setBoardImage(board.getImage().getFileUrl());
        dto.setHit(board.getBoardHit().getHit());
        dto.setCreatedAt(board.getCreatedAt());
        dto.setUpdated(board.getUpdatedAt());
        dto.setDeletedAt(board.getDeletedAt());
        return dto;
    }
}
