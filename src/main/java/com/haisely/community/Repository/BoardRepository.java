package com.haisely.community.Repository;

import com.haisely.community.Entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    List<Board> findAllByDeletedAtIsNull();

    Optional<Board> findByIdAndDeletedAtIsNull(int id);

    Board save(Board board);

    void update(Board board);

    void deleteBoardByUserId(int id);

    // updatedeletedatbyid 랑 user도 -> update로 해야겠네
}
