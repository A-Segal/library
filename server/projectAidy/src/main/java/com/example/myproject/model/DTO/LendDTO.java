package com.example.myproject.model.DTO;

import java.time.LocalDate;

public record LendDTO(Long id, LocalDate lendingDate, LocalDate returnDate,String bookName,Long userId) {
}
