package com.haisely.community.Repository;

import com.haisely.community.Entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findCommentByIdAndDeletedAtIsNull(int id);;

    List<Comment> findCommentsByBoardIdAndDeletedAtIsNull(int id);
}
