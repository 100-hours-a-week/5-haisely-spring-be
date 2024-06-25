package com.haisely.community.Repository.Impl;

import com.haisely.community.Entity.Board;
import com.haisely.community.Repository.BoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Integer>, BoardRepository {

}