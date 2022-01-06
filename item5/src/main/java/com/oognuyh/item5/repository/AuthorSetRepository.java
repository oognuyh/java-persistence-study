package com.oognuyh.item5.repository;

import com.oognuyh.item5.model.AuthorSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorSetRepository extends JpaRepository<AuthorSet, Long> {
    
}
