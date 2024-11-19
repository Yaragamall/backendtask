package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.BorrowingHistory;

import java.util.List;

public interface BorrowServiceInt {
    void borrowBook(Integer userId, Integer bookId);
    List<BorrowingHistory> getBorrowingHistory(Integer userId);
    }
