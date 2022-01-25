package com.oognuyh.item19.repository;

import java.util.Optional;

import com.oognuyh.item19.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    Optional<Author> findByName(String name);
}
