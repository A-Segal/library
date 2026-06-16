package com.example.myproject.model.service;

import com.example.myproject.model.DTO.LendDTO;
import com.example.myproject.model.model.Comment;
import com.example.myproject.model.model.Lend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LendRepository extends JpaRepository<Lend,Long> {


    List<Lend> getLendByUsersId(long userId);

}
