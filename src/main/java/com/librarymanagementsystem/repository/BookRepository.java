package com.librarymanagementsystem.repository;
import com.librarymanagementsystem.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByTitleContaining(String author);
    List<BookEntity> findByAuthorContaining(String author);
    List<BookEntity> findByTitleContainingAndAuthorContaining(String title, String author);
}
