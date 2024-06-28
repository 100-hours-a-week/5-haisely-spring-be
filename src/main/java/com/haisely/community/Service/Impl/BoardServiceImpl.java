package com.haisely.community.Service.Impl;

import com.haisely.community.DTO.Board.BoardDTO;
import com.haisely.community.DTO.Board.BoardDetailDTO;
import com.haisely.community.DTO.Board.BoardIdDTO;
import com.haisely.community.DTO.Board.NewBoardReqDTO;
import com.haisely.community.Entity.Board;
import com.haisely.community.Entity.Comment;
import com.haisely.community.Entity.User;
import com.haisely.community.Exception.ResourceNotFoundException;
import com.haisely.community.Mapper.BoardMapper;
import com.haisely.community.Repository.*;
import com.haisely.community.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final BoardHitRepository boardHitRepository;

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, CommentRepository commentRepository, ImageRepository imageRepository, UserRepository userRepository, BoardHitRepository boardHitRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.boardHitRepository = boardHitRepository;
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
        Board b = boardMapper.toBoard(req);
        // user 찾아서 넣기  (jwt 토큰 활용) >>>  수정 필요
        User u = userRepository.findUserByIdAndDeletedAtIsNull(1)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id 1"));
        if (req.getAttachFilePath() != null){

            // 사진 저장하고 image 가져오기
            // b에 이미지 저장
        }
        // board hit 만들고 지정하기



        return null;
    }


}
