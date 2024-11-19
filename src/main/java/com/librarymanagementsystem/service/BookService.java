package com.librarymanagementsystem.service;

import com.librarymanagementsystem.Entity.BookEntity;
import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements BookServiceInt {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Book> searchBooks(String title , String author){
        List<Book> books = new ArrayList<>();
        List<BookEntity> bookEntities = new ArrayList<>();
        if (title != null && author != null){
            bookEntities =  bookRepository.findByTitleContainingAndAuthorContaining(title,author);
            books = bookEntities.stream().map(bookEntity -> modelMapper.map(bookEntity, Book.class)).toList();
            return books;
        } else if ( title != null) {
            bookEntities = bookRepository.findByTitleContaining(title);
            books = bookEntities.stream().map(bookEntity -> modelMapper.map(bookEntity, Book.class)).toList();
        } else if ( author != null) {
            bookEntities = bookRepository.findByAuthorContaining(author);
            books = bookEntities.stream().map(bookEntity -> modelMapper.map(bookEntity, Book.class)).toList();
        }
        bookEntities = bookRepository.findAll();
        books = bookEntities.stream().map(bookEntity -> modelMapper.map(bookEntity, Book.class)).collect(Collectors.toList());
        return books;
    }
    public Book saveBook(Book book){
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        bookEntity = bookRepository.save(bookEntity);
        return modelMapper.map(bookEntity, Book.class);
    }
}
