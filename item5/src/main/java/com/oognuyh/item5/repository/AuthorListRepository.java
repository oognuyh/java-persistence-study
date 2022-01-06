package com.oognuyh.item5.repository;

import com.oognuyh.item5.model.AuthorList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorListRepository extends JpaRepository<AuthorList, Long> {
    
}
