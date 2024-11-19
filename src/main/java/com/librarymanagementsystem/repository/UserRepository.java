package com.librarymanagementsystem.repository;
import com.librarymanagementsystem.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
