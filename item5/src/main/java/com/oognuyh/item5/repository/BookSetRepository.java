package com.oognuyh.item5.repository;

import com.oognuyh.item5.model.BookSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSetRepository extends JpaRepository<BookSet, Long> {
    
}
