package com.librarymanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_history")
@Getter
@Setter
public class BorrowingHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookEntity;


    private LocalDate borrowDate;
    private LocalDate returnDate;
}
