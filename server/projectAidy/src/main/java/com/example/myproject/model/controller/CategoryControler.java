package com.example.myproject.model.controller;

import com.example.myproject.model.model.Book;
import com.example.myproject.model.model.Category;
import com.example.myproject.model.service.mappers.CategoryMapper;
import com.example.myproject.model.service.CategoryRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/category")
@RestController
@CrossOrigin

public class CategoryControler {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    public CategoryControler(CategoryRepository categoryRepository,CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/getCategorys")
    public ResponseEntity<List<Category>> getCategorys() {
        try {
            return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        try{
            Category c=categoryRepository.findById(id).orElse(null);
            if(c==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addCategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category c) {
        try{
            Category newCategory=categoryRepository.save(c);
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateCategory(@PathVariable long id, @RequestBody Category c) {
        try{
            Category c1=categoryRepository.findById(id).orElse(null);
            if(c1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            c1.setBookList(c.getBookList());
            c1.setColor( c.getColor() );
            c1.setId(c1.getId());
            c1.setDescription(c.getDescription());
            c1.setName(c.getName());

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteCategory(@PathVariable long id) {
        try{
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
