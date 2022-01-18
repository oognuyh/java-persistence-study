package com.oognuyh.item12.repository;

import com.oognuyh.item12.model.Magazine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    
}
