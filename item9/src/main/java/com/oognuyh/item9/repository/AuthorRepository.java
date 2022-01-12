package com.oognuyh.item9.repository;

import java.util.List;

import com.oognuyh.item9.model.Author;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    String AUTHOR_BOOKS_GRAPH = "author-books-graph";

    @EntityGraph(
        value = AUTHOR_BOOKS_GRAPH,
        type = EntityGraphType.FETCH
    )
    List<Author> findByAgeGreaterThanAndGenre(int age, String genre);

    @EntityGraph(
        value = AUTHOR_BOOKS_GRAPH,
        type = EntityGraphType.LOAD
    )
    List<Author> findByGenreAndAgeGreaterThan(String genre, int age);
}
