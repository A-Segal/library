package com.example.myproject.model.controller;

import com.example.myproject.model.DTO.BookDTO;
import com.example.myproject.model.DTO.CommendDTO;
import com.example.myproject.model.model.Book;
import com.example.myproject.model.model.Comment;
import com.example.myproject.model.service.mappers.CommentMapper;
import com.example.myproject.model.service.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin
public class CommentControler {
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    public CommentControler(CommentRepository commentRepository,CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/getComments")
    public ResponseEntity<List<Comment>> getComments() {
        try {
            return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getComment/{id}")
    public ResponseEntity<CommendDTO> getCommentById(@PathVariable long id) {
        try{
            Comment c=commentRepository.findById(id).orElse(null);
            if(c==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(commentMapper.toDTO(c), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addComment")
    public ResponseEntity<CommendDTO> createComment(@RequestBody  Comment c) {
        try{
            Comment newComment=commentRepository.save(c);
            return new ResponseEntity<>(commentMapper.toDTO(newComment), HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CommendDTO> updateComment(@PathVariable long id, @RequestBody Comment c) {
        try{
            Comment c1=commentRepository.findById(id).orElse(null);
            if(c1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            c1.setBook(c.getBook());
            c1.setContent( c.getContent() );
            c1.setDate(c.getDate());
            c1.setUsers( c.getUsers() );
            c1.setId(c1.getId());

            commentRepository.save(c1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDTO> deleteComment(@PathVariable long id) {
        try{
            commentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getCommentsbyBookId/{bookId}")
    public ResponseEntity<List<CommendDTO> >findCommentsByBookId(@PathVariable  Long bookId){
        try{
            List<Comment> c=commentRepository.findCommentsByBookId(bookId);
            if(c==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(commentMapper.toDTO(c), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
