package com.haisely.community.Service;

import com.haisely.community.DTO.Board.BoardDTO;
import com.haisely.community.DTO.Board.BoardDetailDTO;
import com.haisely.community.Entity.Board;

import java.util.List;

public interface BoardService {
    public List<BoardDTO> getBoards();
    public BoardDetailDTO getBoardDetailById(int id);
}
