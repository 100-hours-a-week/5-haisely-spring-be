package com.haisely.community.Repository.Impl;

import com.haisely.community.Entity.Board;
import com.haisely.community.Repository.BoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Integer>, BoardRepository {
    @Modifying
    @Query("UPDATE boards b SET b.title = :#{#board.title}, b.content = :#{#board.content}, b.image.fileUrl = :#{#board.image.fileUrl} WHERE b.id = :#{#board.id}")
    void update(@Param("board") Board board);
    // 유지보수는 힘들지만 성능은 좋아진다 - from noah. jo ? (jo tae hyeon)
    // jpql은 여기서만 쓰는 거임

    @Modifying
    @Query("UPDATE boards b set b.deleted_at = CURRENT_TIMESTAMP WHERE b.user_id = :#{id};")
    void deleteBoardByUserId(@Param("id") int id);
}