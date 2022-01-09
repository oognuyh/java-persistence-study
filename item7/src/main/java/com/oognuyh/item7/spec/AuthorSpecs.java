package com.oognuyh.item7.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.oognuyh.item7.model.Author;

import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecs {
    
    public static Specification<Author> isAgeGreaterThan(int age) {
        return (Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
                -> builder.greaterThan(root.get("age"), age);
    }
}
