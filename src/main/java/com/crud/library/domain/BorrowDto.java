package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BorrowDto {
    private long id;
    private Book book;
    private User user;
    private LocalDate dateOfBorrow;
    private LocalDate dateOfReturn;
}
