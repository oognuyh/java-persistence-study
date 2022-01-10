package com.oognuyh.item8.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.oognuyh.item8.model.Publisher;

import org.springframework.data.jpa.domain.Specification;

public class PublisherSpecs {
    
    public static Specification<Publisher> isIdGreaterThan(long id) {
        return (Root<Publisher> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.greaterThan(root.get("id"), id);
    }
}
