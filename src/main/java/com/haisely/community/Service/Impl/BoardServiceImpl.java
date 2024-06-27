package com.haisely.community.Service.Impl;

import com.haisely.community.DTO.Board.BoardDTO;
import com.haisely.community.DTO.Board.BoardDetailDTO;
import com.haisely.community.DTO.Board.BoardIdDTO;
import com.haisely.community.DTO.Board.NewBoardReqDTO;
import com.haisely.community.Entity.Board;
import com.haisely.community.Entity.Comment;
import com.haisely.community.Exception.ResourceNotFoundException;
import com.haisely.community.Mapper.BoardMapper;
import com.haisely.community.Repository.BoardRepository;
import com.haisely.community.Repository.CommentRepository;
import com.haisely.community.Service.BoardService;
import com.haisely.community.Util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, CommentRepository commentRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.boardMapper = boardMapper;
    }

    @Override
    public List<BoardDTO> getBoards() {
        List<Board> boards = boardRepository.findAllByDeletedAtIsNull();
        return boards.stream()
                .map(boardMapper::toBoardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BoardDetailDTO getBoardDetailById(int id) {
        Board board = boardRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id " + id));

        BoardDetailDTO dto = new BoardDetailDTO();
        dto.setBoard(boardMapper.toBoardDTO(board));

        List<Comment> comments = commentRepository.findCommentsByBoardIdAndDeletedAtIsNull(id);
        dto.setComments(comments.stream()
                .map(boardMapper::toCommentDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    @Override
    public BoardIdDTO saveBoard(NewBoardReqDTO req) {
        return null;
    }


}
