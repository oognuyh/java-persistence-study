package com.oognuyh.item24.repository;

import com.oognuyh.item24.model.AuthorShallow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorShallowRepository extends JpaRepository<AuthorShallow, Long> {
    
}
