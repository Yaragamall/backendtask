package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.model.BorrowingHistory;
import com.librarymanagementsystem.service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowedController {


    private BorrowService borrowService;

    @PostMapping
    public ResponseEntity<String> borrowBook(@RequestParam Integer userId , @RequestParam Integer bookId) {
        borrowService.borrowBook(userId, bookId);
        return ResponseEntity.ok("Book borrowed successfully!");
    }
    @GetMapping("/history/{userId}")
    public List<BorrowingHistory> getHistory(@PathVariable Integer userId) {
        return borrowService.getBorrowingHistory(userId);
    }
}
