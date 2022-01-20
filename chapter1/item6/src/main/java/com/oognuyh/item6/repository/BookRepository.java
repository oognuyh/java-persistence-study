package com.oognuyh.item6.repository;

import java.util.List;

import com.oognuyh.item6.model.Author;
import com.oognuyh.item6.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    Book findByTitle(String title);
   
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM book b WHERE b.author.id = ?1")
    public int deleteByAuthorId(Long id);
    
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM book b WHERE b.author.id IN ?1")
    public int deleteByAuthorIds(List<Long> id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM book b WHERE b.author IN ?1")
    public int deleteByAuthors(List<Author> authors);
}
