package com.oognuyh.item8.repository;

import java.util.List;

import com.oognuyh.item8.model.Publisher;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>, JpaSpecificationExecutor<Publisher> {
    
    @Override
    @EntityGraph(
        attributePaths = { "books.author" },
        type = EntityGraphType.FETCH
    )
    public List<Publisher> findAll();

    @EntityGraph(
        attributePaths = { "books.author" },
        type = EntityGraphType.FETCH
    )
    public List<Publisher> findByIdLessThanOrderByCompanyDesc(long id);

    @Override
    @EntityGraph(
        attributePaths = { "books.author" },
        type = EntityGraphType.FETCH
    )
    public List<Publisher> findAll(Specification<Publisher> spec);

    @EntityGraph(
        attributePaths = { "books.author" },
        type = EntityGraphType.FETCH
    )
    @Query("SELECT p FROM publisher p WHERE 1 < p.id AND p.id < 3")    
    public List<Publisher> findByIdBetween1And3();
}
