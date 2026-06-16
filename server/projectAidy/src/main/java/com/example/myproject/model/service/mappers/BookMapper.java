package com.example.myproject.model.service.mappers;

import com.example.myproject.model.DTO.BookDTO;
import com.example.myproject.model.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "category.name",target = "CategoryName")
    BookDTO toBookDTO(Book b);


    Book toBook(BookDTO bookDTO);

    List<BookDTO> toBookDTO(List<Book> bookList);

}




