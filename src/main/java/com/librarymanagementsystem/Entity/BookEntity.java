package com.librarymanagementsystem.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.relational.core.mapping.Table;
@Entity
@Table(name = "books")
@Getter
@Setter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String title;


    private String author;


    @Column(unique = true , length = 13)
    private String isbn;


    private int copiesAvailable;
}
