package com.example.librarymanagement.config;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.User;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void run(String... args) {
        if (bookRepo.count() == 0) {
            bookRepo.save(new Book(null, "Java Fundamentals", "James Gosling", "Programming", true));
            bookRepo.save(new Book(null, "Spring Boot Guide", "Craig Walls", "Framework", true));
        }

        if (userRepo.count() == 0) {
            userRepo.save(new User(null, "Alice", "Student"));
            userRepo.save(new User(null, "Bob", "Faculty"));
        }
    }
}
