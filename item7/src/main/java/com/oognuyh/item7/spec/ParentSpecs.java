package com.oognuyh.item7.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.oognuyh.item7.model.Parent;

import org.springframework.data.jpa.domain.Specification;

public class ParentSpecs {
    
    public static Specification<Parent> isAgeGreaterThan(int age) {
        return (Root<Parent> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.greaterThan(root.get("age"), age);
    }
}
