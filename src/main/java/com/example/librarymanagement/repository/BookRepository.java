// package com.example.librarymanagement.repository;

// import com.example.librarymanagement.model.Book;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface BookRepository extends JpaRepository<Book, Long> {
//     List<Book> findByTitleContainingIgnoreCase(String title);
//     List<Book> findByAuthorContainingIgnoreCase(String author);
//     List<Book> findByCategoryContainingIgnoreCase(String category);
// }
package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
}
