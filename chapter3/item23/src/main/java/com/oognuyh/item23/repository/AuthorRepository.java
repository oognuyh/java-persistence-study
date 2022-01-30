package com.oognuyh.item23.repository;

import java.util.List;

import com.oognuyh.item23.dto.AuthorDto;
import com.oognuyh.item23.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    List<Author> findByAgeGreaterThanEqual(Integer age);

    @Query("SELECT a.name AS name, a.avatar AS avatar FROM author a WHERE a.age >= ?1")
    List<AuthorDto> findAuthorDtosByAgeGreaterThanEqual(Integer age);
}
