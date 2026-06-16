package com.example.myproject.model.controller;

import com.example.myproject.model.DTO.CommendDTO;
import com.example.myproject.model.DTO.LendDTO;
import com.example.myproject.model.model.Comment;
import com.example.myproject.model.model.Lend;
import com.example.myproject.model.service.mappers.LendMapper;
import com.example.myproject.model.service.LendRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lend")
@CrossOrigin

public class LendControler {
    private LendRepository lendRepository;
    private LendMapper lendMapper;
    public LendControler(LendRepository lendRepository,LendMapper lendMapper) {
        this.lendRepository = lendRepository;
        this.lendMapper = lendMapper;
    }

    @GetMapping("/getLends")
    public ResponseEntity<List<LendDTO>> getLends() {
        try {
            return new ResponseEntity<>(lendMapper.lendToDTO(lendRepository.findAll()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getLend/{id}")
    public ResponseEntity<LendDTO> getLendById(@PathVariable long id) {
        try{
            Lend l=lendRepository.findById(id).orElse(null);
            if(l==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(lendMapper.toDTO(l), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addLend")
    public ResponseEntity<LendDTO> createLend(@RequestBody Lend l) {
        try{
            LendDTO newLend=lendMapper.toDTO(lendRepository.save(l));
            return new ResponseEntity<>(newLend, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Lend> updateLend(@PathVariable long id, @RequestBody Lend l) {
        try{
            Lend l1=lendRepository.findById(id).orElse(null);
            if(l1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            l1.setLendingDate(l.getLendingDate());
            l1.setBook(l.getBook());
            l1.setId(l.getId());
            l1.setUsers(l.getUsers());
            l1.setReturnDate(l.getReturnDate());

            lendRepository.save(l1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Lend> deleteLend(@PathVariable long id) {
        try{
            lendRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getLends/{userId}")
    public ResponseEntity<List<LendDTO>>getLendByUserId(@PathVariable long userId) {
        try{
            List<LendDTO> l=lendMapper.lendToDTO(lendRepository.getLendByUsersId(userId));
            if(l==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(l ,HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}




