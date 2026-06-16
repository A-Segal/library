package com.example.myproject.model.service.mappers;

import com.example.myproject.model.DTO.LendDTO;
import com.example.myproject.model.model.Lend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LendMapper {
    Lend toLend(LendDTO lendDTO);
    @Mapping(source = "users.id",target = "userId")

    @Mapping(source = "book.title",target = "bookName")
    LendDTO toDTO(Lend l);

    List<LendDTO> lendToDTO(List<Lend> lendByUserId);
}