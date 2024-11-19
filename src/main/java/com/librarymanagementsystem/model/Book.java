package com.librarymanagementsystem.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;



@Getter
@Setter
public class Book {

    private Integer id;


    private String title;

    private String author;


    private String isbn;

    @Min(0)
    private int copiesAvailable;
}
