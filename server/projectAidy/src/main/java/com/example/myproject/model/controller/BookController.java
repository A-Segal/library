package com.example.myproject.model.controller;

import com.example.myproject.model.DTO.BookDTO;
import com.example.myproject.model.model.Book;
import com.example.myproject.model.service.mappers.BookMapper;
import com.example.myproject.model.service.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
public class BookController {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    public BookController(BookRepository bookRepository,BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/getBooks")
    public ResponseEntity<List<BookDTO>> getBooks() {
        try {
            return new ResponseEntity<>(bookMapper.toBookDTO(bookRepository.findAll()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable long id) {
         try{
             Book b=bookRepository.findById(id).orElse(null);
             if(b==null){
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
             return new ResponseEntity<>(bookMapper.toBookDTO(b), HttpStatus.OK);
         }
         catch(Exception e){
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
    @PostMapping("/addBook")
    public ResponseEntity<BookDTO> createBook(@RequestBody Book b) {
        try{
            Book newBook=bookRepository.save(b);
            return new ResponseEntity<>(bookMapper.toBookDTO(newBook), HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
   @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable long id, @RequestBody Book b) {
        try{
            Book b1=bookRepository.findById(id).orElse(null);
            if(b1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            b1.setAuthor(b.getAuthor());
            b1.setTitle(b.getTitle());
            b1.setCategory(b.getCategory());
            b1.setId(b.getId());
            b1.setImage(b.getImage());
            b1.setLendList(b.getLendList());
            b1.setListCommend(b.getListCommend());
            b1.setPageCount(b.getPageCount());
            b1.setSummery(b.getSummery());
            bookRepository.save(b1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

   }
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable long id) {
        try{
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
}
