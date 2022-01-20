package com.oognuyh.item7.repository;

import java.util.List;

import com.oognuyh.item7.model.Book;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    String BOOK_AUTHOR_GRAPH = "book-author-graph";

    @Override
    @EntityGraph(
        value = BOOK_AUTHOR_GRAPH,
        type = EntityGraphType.FETCH
    )
    List<Book> findAll();
}
