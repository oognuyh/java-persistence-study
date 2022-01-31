package com.oognuyh.item24.repository;

import com.oognuyh.item24.model.AuthorDeep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDeepRepository extends JpaRepository<AuthorDeep, Long> {
    
}
