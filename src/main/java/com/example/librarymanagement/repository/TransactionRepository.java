package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Transaction;
import com.example.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
