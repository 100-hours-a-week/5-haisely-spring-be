package com.haisely.community.Service.Impl;

import com.haisely.community.Repository.CommentRepository;
import com.haisely.community.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
}
