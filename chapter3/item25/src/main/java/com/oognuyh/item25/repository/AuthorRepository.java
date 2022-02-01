package com.oognuyh.item25.repository;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.oognuyh.item25.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    List<AuthorSummary> findAuthorSummariesByGenre(String genre);

    String findNameById(@Param("id") Long id);

    AuthorSummary findNameAndAgeById(@Param("id") Long id);

    @Query("SELECT a.age AS age, a.genre AS genre FROM author a WHERE a.id = ?1")
    AuthorDto findAgeAndGenreById(Long id);

    <T> T findByName(String name, Class<T> clazz);

    static interface AuthorSummary {

        String getName();

        Integer getAge();
    }

    @JsonInclude(Include.NON_DEFAULT)
    static interface AuthorDto {

        String getName();

        Integer getAge();

        String getGenre();
    }
}
