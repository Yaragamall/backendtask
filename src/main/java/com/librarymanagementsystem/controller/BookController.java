package com.librarymanagementsystem.controller;
import com.librarymanagementsystem.Entity.BookEntity;
import com.librarymanagementsystem.model.Role;
import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {


    private BookService bookService;

    @GetMapping
    public List<Book> searchBooks(@RequestParam(required=false) String title , @RequestParam(required=false) String author) {
        return bookService.searchBooks(title,author);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book book , @RequestParam Role role) {
        if (!role.equals(Role.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to add this book");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(book));
    }
}
