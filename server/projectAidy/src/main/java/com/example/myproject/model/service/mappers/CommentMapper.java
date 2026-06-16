package com.example.myproject.model.service.mappers;

import com.example.myproject.model.DTO.CommendDTO;
import com.example.myproject.model.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
//    @Mapping(source = "users.userName", target = "username")
   @Mapping(source = "book.id", target = "bookId")
    CommendDTO toDTO(Comment c);
    Comment toComment(CommendDTO CommentDTO);

    List<CommendDTO> toDTO(List<Comment> comments);
}