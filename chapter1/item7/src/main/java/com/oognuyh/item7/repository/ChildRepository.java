package com.oognuyh.item7.repository;

import java.util.List;

import com.oognuyh.item7.model.Child;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    
    @Override
    @EntityGraph(
        attributePaths = { "parent" },
        type = EntityGraphType.FETCH
    )
    List<Child> findAll();
}
