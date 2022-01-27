package com.oognuyh.item21.repository;

import java.util.Optional;

import com.oognuyh.item21.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @Query("SELECT a FROM author a WHERE a.id = ?1")
    Optional<Author> findByIdViaJpql(Long id);

    @Query(value = "SELECT * FROM author WHERE author.id = ?1", nativeQuery = true)
    Optional<Author> findByIdViaNativeQuery(Long id);

    @Query("SELECT a.name FROM author a WHERE a.id = ?1")
    Optional<String> findNameByIdViaJpql(Long id);

    @Query(value = "SELECT name FROM author WHERE author.id = ?1", nativeQuery = true)
    Optional<String> findNameByIdViaNativeQuery(Long id);
}
