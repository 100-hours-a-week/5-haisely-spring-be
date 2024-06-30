package com.haisely.community.repository;

import com.haisely.community.Entity.Board;
import com.haisely.community.Repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// jpa만 테스트하는데 테스트 중에 실제 데이터베이스를 사용함 -> @AutoConfig 어노테이션이 없는데 h2가 없으면 터짐!
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardJpaRepository;

    @Test
    public void 보드_조회() throws Exception {
        // When: 서비스 메서드 호출
        List<Board> boards = boardJpaRepository.findAllByDeletedAtIsNull();

        // Then: 결과 검증
        int size = boards.size();

        Board b = Board.builder()
                .title("하이욤")
                .content("ㅋㅋ")
                .build();
        boardJpaRepository.save(b);

        List<Board> boards2 = boardJpaRepository.findAllByDeletedAtIsNull();

        // Then: 결과 검증
        assertEquals(size+1, boards2.size());
    }
}
