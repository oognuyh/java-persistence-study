package com.oognuyh.item15.repository;

import java.util.Optional;

import com.oognuyh.item15.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    Optional<Book> findByTitle(String title);
}
