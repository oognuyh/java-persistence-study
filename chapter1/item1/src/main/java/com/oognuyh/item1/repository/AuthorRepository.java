package com.oognuyh.item1.repository;

import java.util.Optional;

import com.oognuyh.item1.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @Query("SELECT a FROM author a JOIN FETCH a.books WHERE a.name = ?1")
    Optional<Author> findByName(String name);
}
