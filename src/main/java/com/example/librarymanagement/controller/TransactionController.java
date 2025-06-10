package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Transaction;
import com.example.librarymanagement.model.User;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.TransactionRepository;
import com.example.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "transactions";
    }

    @PostMapping("/lend")
    public String lendBook(@RequestParam Long bookId, @RequestParam Long userId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (book != null && book.isAvailability() && user != null) {
            Transaction transaction = new Transaction();
            transaction.setBook(book);
            transaction.setUser(user);
            transaction.setIssueDate(LocalDate.now());
            transaction.setStatus("Issued");
            transactionRepository.save(transaction);

            book.setAvailability(false);
            bookRepository.save(book);
        }

        return "redirect:/transactions";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null && transaction.getStatus().equals("Issued")) {
            transaction.setReturnDate(LocalDate.now());
            transaction.setStatus("Returned");
            transactionRepository.save(transaction);

            Book book = transaction.getBook();
            book.setAvailability(true);
            bookRepository.save(book);
        }
        return "redirect:/transactions";
    }
}
