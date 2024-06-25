package com.haisely.community.Service.Impl;

import com.haisely.community.Entity.Board;
import com.haisely.community.Repository.BoardRepository;
import com.haisely.community.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getBoards() {
        return boardRepository.findAllBoards();
    }
}
