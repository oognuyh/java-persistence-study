package com.oognuyh.item7.repository;

import java.util.List;

import com.oognuyh.item7.model.Author;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
    
    String AUTHOR_BOOKS_GRAPH = "author-books-graph";

    @Override
    @EntityGraph(
        value = AUTHOR_BOOKS_GRAPH,
        type = EntityGraphType.FETCH
    )
    List<Author> findAll();

    @Override
    @EntityGraph(
        value = AUTHOR_BOOKS_GRAPH,
        type = EntityGraphType.FETCH
    )
    List<Author> findAll(Specification<Author> spec);

    @EntityGraph(
        value = AUTHOR_BOOKS_GRAPH,
        type = EntityGraphType.FETCH
    )
    List<Author> findByAgeLessThanOrderByNameDesc(int age);

    @EntityGraph(
        value = AUTHOR_BOOKS_GRAPH,
        type = EntityGraphType.FETCH
    )
    @Query(value="SELECT a FROM author a WHERE 20 < a.age AND a.age < 40")
    List<Author> findByAgeBetween20And40();
}
