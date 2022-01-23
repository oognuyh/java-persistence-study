package com.oognuyh.item17.repository;

import com.oognuyh.item17.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @Query("SELECT a FROM author a JOIN FETCH a.books WHERE a.name = ?1")
    Author findByName(String name);
}
