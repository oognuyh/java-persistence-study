package com.oognuyh.item3.repository;

import java.util.List;

import com.oognuyh.item3.model.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM book b WHERE b.author.id = ?1")
    List<Book> findByAuthorId(Long authorId);

    @Query("SELECT b FROM book b WHERE b.author.id = :id")
    Page<Book> findByAuthorId(Long authorId, Pageable pageable);
}
