package com.example.myproject.model.service;

import com.example.myproject.model.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByTitleContains(String title);

}
