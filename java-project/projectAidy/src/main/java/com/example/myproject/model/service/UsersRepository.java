package com.example.myproject.model.service;

import com.example.myproject.model.model.Comment;
import com.example.myproject.model.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users getUserByPassword(String password,String userName);

}
