package com.haisely.community.service;

import com.haisely.community.DTO.Board.BoardDTO;
import com.haisely.community.DTO.Board.BoardDetailDTO;
import com.haisely.community.Entity.Board;
import com.haisely.community.Repository.Impl.BoardJpaRepository;
import com.haisely.community.Service.BoardService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BoardServiceRealRepTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardJpaRepository boardJpaRepository;

    @Test
    public void 보드_조회() throws Exception {
        // When: 서비스 메서드 호출
        List<BoardDTO> boards = boardService.getBoards();

        // Then: 결과 검증
        assertEquals(2, boards.size());

        boards.forEach(board -> {
            System.out.println("Board ID: " + board.getBoardId());
            System.out.println("User nickname: " + board.getNickname());
            System.out.println("Board Title: " + board.getTitle());
            System.out.println("Board Content: " + board.getContent());
            System.out.println("Board Created At: " + board.getCreatedAt());
        });
    }

    @Test
    public void 보드_댓글_조회() throws Exception {
        BoardDetailDTO dto = boardService.getBoardDetailById(1);
        assertEquals(4, dto.getComments().size());
    }
}