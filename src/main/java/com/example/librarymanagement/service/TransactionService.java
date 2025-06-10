package com.example.librarymanagement.service;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Transaction;
import com.example.librarymanagement.model.User;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.TransactionRepository;
import com.example.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Transaction issueBook(Long bookId, Long userId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (bookOpt.isPresent() && userOpt.isPresent()) {
            Book book = bookOpt.get();
            if (!book.isAvailability()) {
                throw new RuntimeException("Book is not available");
            }
            book.setAvailability(false);
            bookRepository.save(book);

            Transaction transaction = new Transaction();
            transaction.setBook(book);
            transaction.setUser(userOpt.get());
            transaction.setIssueDate(LocalDate.now());
            transaction.setStatus("Issued");

            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Book or User not found");
        }
    }

    public Transaction returnBook(Long transactionId) {
        Optional<Transaction> transactionOpt = transactionRepository.findById(transactionId);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            Book book = transaction.getBook();
            book.setAvailability(true);
            bookRepository.save(book);

            transaction.setReturnDate(LocalDate.now());
            transaction.setStatus("Returned");
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    public List<Transaction> getUserTransactions(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return transactionRepository.findByUser(userOpt.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
