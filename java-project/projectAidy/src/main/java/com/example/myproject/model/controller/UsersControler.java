package com.example.myproject.model.controller;

import com.example.myproject.model.DTO.UsersDTO;
import com.example.myproject.model.model.Users;
import com.example.myproject.model.service.mappers.UsersMapper;
import com.example.myproject.model.service.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UsersControler {

    private UsersRepository usersRepository;
    private UsersMapper usersMapper;
    public UsersControler(UsersRepository usersRepository,UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getUsers() {
        try {
            return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable long id) {
        try{
            Users u=usersRepository.findById(id).orElse(null);
            if(u==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getUserByPassword")
    public ResponseEntity<Users> getUserByPassword(@RequestBody Users x) {
        try{
            Users u=usersRepository.getUserByPassword(x.getPassword(),x.getUserName());
            if(u==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addUser")
    public ResponseEntity<Users> createUser(@RequestBody Users u) {
        try{
            Users newUser=usersRepository.save(u);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable long id, @RequestBody Users u) {
        try{
            Users u1=usersRepository.findById(id).orElse(null);
            if(u1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            u1.setUserName(u.getUserName());
            u1.setMail(u.getMail());
            u1.setPassword(u.getPassword());
            u1.setCommentList(u.getCommentList());
            u1.setLastName(u.getLastName());
            u1.setFirstName(u.getFirstName());
            u1.setLendList(u.getLendList());
            u1.setTz(u.getTz());
            u1.setStatus(u.isStatus());
            u1.setPhoneNumber(u.getPhoneNumber());
            u1.setId(u1.getId());

            usersRepository.save(u1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable long id) {
        try{
            usersRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
