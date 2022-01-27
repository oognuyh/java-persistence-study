package com.oognuyh.item21.repository;

import java.util.List;

import com.oognuyh.item21.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("SELECT b FROM book b WHERE b.id in ?1")
    List<Book> findBooksById(List<Long> ids);
}
