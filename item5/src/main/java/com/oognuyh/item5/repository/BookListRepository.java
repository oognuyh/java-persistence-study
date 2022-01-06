package com.oognuyh.item5.repository;

import com.oognuyh.item5.model.BookList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookListRepository extends JpaRepository<BookList, Long> {
    
}
