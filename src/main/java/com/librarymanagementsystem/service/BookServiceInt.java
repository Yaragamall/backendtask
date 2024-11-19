package com.librarymanagementsystem.service;


import com.librarymanagementsystem.Entity.BookEntity;
import com.librarymanagementsystem.model.Book;

import java.util.List;

public interface BookServiceInt {

    List<Book> searchBooks(String title , String author);
    Book saveBook(Book book);

}
