package com.example.myproject.model.service;

import com.example.myproject.model.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByBookId(Long bookId);
}
