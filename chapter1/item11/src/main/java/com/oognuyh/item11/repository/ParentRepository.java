package com.oognuyh.item11.repository;

import com.oognuyh.item11.model.Parent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    
}
