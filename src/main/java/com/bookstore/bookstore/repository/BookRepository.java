package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitle(String title);
    List<Book> findByAuthors_Name(String name);

}
