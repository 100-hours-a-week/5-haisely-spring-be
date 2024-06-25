package com.haisely.community.Repository;

import com.haisely.community.Entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    List<Board> findAllBoards();

    Optional<Board> findBoardById(int id);
}
