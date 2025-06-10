package com.example.librarymanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String category;

    private boolean availability = true;

    // Getters and Setters
    public Book() {}
    public Book(Long bookId, String title, String author, String category, boolean availability) {
    this.bookId = bookId;
    this.title = title;
    this.author = author;
    this.category = category;
    this.availability = availability;
}
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}
