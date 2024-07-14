package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book bookDetails) {
        return ResponseEntity.ok(bookService.updateBook(isbn, bookDetails));
    }

    @GetMapping
    public ResponseEntity<List<Book>> findBooksByTitleOrAuthor(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        if (title != null) {
            return ResponseEntity.ok(bookService.findBooksByTitle(title));
        } else if (author != null) {
            return ResponseEntity.ok(bookService.findBooksByAuthor(author));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{isbn}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }
}