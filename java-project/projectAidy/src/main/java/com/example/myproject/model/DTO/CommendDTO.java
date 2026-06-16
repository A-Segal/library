package com.example.myproject.model.DTO;

import java.time.LocalDate;

public record CommendDTO(Long id, String content, LocalDate date, Long bookId, String username) {

}
