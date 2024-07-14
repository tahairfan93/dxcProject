package com.bookstore.bookstore.service;

import com.bookstore.bookstore.ExceptionHandler.ResourceNotFoundException;
import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(String isbn, Book bookDetails) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new ResourceNotFoundException("Book not found for this isbn :: " + isbn));
        book.setTitle(bookDetails.getTitle());
        book.setYear(bookDetails.getYear());
        book.setPrice(bookDetails.getPrice());
        book.setGenre(bookDetails.getGenre());
        book.setAuthors(bookDetails.getAuthors());
        return bookRepository.save(book);
    }

    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthors_Name(author);
    }

    public void deleteBook(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new ResourceNotFoundException("Book not found for this isbn :: " + isbn));
        bookRepository.delete(book);
    }
}
