package com.haisely.community.Repository.Impl;

import com.haisely.community.Entity.BoardHit;
import com.haisely.community.Repository.BoardHitRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardHitJpaRepository extends JpaRepository<BoardHit, Integer>, BoardHitRepository {
}
