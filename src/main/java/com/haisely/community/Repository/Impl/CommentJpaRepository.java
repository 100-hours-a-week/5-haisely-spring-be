package com.haisely.community.Repository.Impl;


import com.haisely.community.Entity.Comment;
import com.haisely.community.Repository.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Integer>, CommentRepository {
    // deleted null인 애들만 긁어와야함
}