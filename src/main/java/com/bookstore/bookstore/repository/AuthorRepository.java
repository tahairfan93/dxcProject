package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author,Long>{


}
