package com.librarymanagementsystem.service;

import com.librarymanagementsystem.Entity.BookEntity;
import com.librarymanagementsystem.Entity.BorrowingHistoryEntity;
import com.librarymanagementsystem.Entity.UserEntity;
import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.model.BorrowingHistory;
import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.repository.BorrowingHistoryRepository;
import com.librarymanagementsystem.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Service
public class BorrowService implements BorrowServiceInt{
    @Autowired
    private BorrowingHistoryRepository borrowingHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void borrowBook(Integer userId, Integer bookId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (bookEntity.getCopiesAvailable() <= 0 ) {
            throw new IllegalStateException("Copies not available");
        }

        bookEntity.setCopiesAvailable(bookEntity.getCopiesAvailable() - 1);
        bookRepository.save(bookEntity);

        BorrowingHistory history = new BorrowingHistory();
        history.setUser(modelMapper.map(user, User.class));
        history.setBook(bookEntity);
        history.setDate(LocalDate.now());
        borrowingHistoryRepository.save(modelMapper.map(history, BorrowingHistoryEntity.class));
    }

    public List<BorrowingHistory> getBorrowingHistory(Integer userId) {
        List<BorrowingHistory> borrowingHistories = new ArrayList<>();
        List<BorrowingHistoryEntity> borrowingHistoryEntities = new ArrayList<>();
        borrowingHistoryEntities = borrowingHistoryRepository.findByUserEntity_Id(userId);
        borrowingHistories = borrowingHistoryEntities.stream().map(borrowingHistoryEntity -> modelMapper.map(borrowingHistoryEntity, BorrowingHistory.class)).toList();
        return borrowingHistories ;
    }
}
