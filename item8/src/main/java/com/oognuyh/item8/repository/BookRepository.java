package com.oognuyh.item8.repository;

import com.oognuyh.item8.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    String BOOK_PUBLISHER_GRAPH = "book-publisher-graph";
}
