package com.librarymanagementsystem.repository;
import com.librarymanagementsystem.Entity.BorrowingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingHistoryRepository extends JpaRepository<BorrowingHistoryEntity, Integer> {
    List<BorrowingHistoryEntity> findByUserEntity_Id(Integer userId);
}
