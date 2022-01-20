package com.oognuyh.item6.repository;

import java.util.List;

import com.oognuyh.item6.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    Author findByName(String name);

    List<Author> findByAge(Integer age);

    @Query("SELECT a FROM author a JOIN FETCH a.books WHERE a.name = ?1")
    Author findByNameWithBooks(String name);

    @Query("SELECT a FROM author a JOIN FETCH a.books WHERE a.genre = ?1")
    List<Author> findByGenreWithBooks(String genre);

    @Modifying
    @Query("DELETE FROM author a WHERE a.id = ?1")
    void deleteById(Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM author a WHERE a.id IN ?1")
    void deleteByIds(List<Long> ids);
}
