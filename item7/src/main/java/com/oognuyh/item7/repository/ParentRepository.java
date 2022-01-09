package com.oognuyh.item7.repository;

import java.util.List;

import com.oognuyh.item7.model.Parent;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long>, JpaSpecificationExecutor<Parent> {

    @Override
    @EntityGraph(
        attributePaths = { "children" },
        type = EntityGraphType.FETCH
    )
    List<Parent> findAll();

    @EntityGraph(
        attributePaths = { "children" },
        type = EntityGraphType.FETCH
    )
    public List<Parent> findAll(Specification<Parent> spec);  

    @EntityGraph(
        attributePaths = { "children" },
        type = EntityGraphType.FETCH
    )
    public List<Parent> findByAgeLessThanOrderByNameDesc(int age);

    @EntityGraph(
        attributePaths = { "children" },
        type = EntityGraphType.FETCH
    )
    @Query(value="SELECT p FROM parent p WHERE 20 < p.age AND p.age < 40")
    public List<Parent> findByAgeBetween20And40(); 
}
