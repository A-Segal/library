package com.example.myproject.model.service.mappers;

import com.example.myproject.model.DTO.CategoryDTO;
import com.example.myproject.model.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CategoryMapper {


    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category c);

}
