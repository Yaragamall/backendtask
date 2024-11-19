package com.librarymanagementsystem.model;

import com.librarymanagementsystem.Entity.BookEntity;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
@Getter
@Setter
public class BorrowingHistory {

    private Integer id;

    private User user;

    private BookEntity book;


    private LocalDate date;

    private LocalDate returnDate;
}
