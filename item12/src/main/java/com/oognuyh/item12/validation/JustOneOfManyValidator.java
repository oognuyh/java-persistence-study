package com.oognuyh.item12.validation;

import java.util.Objects;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.oognuyh.item12.model.Review;

public class JustOneOfManyValidator implements ConstraintValidator<JustOneOfMany, Review> {

    @Override
    public boolean isValid(Review review, ConstraintValidatorContext context) {
        return Stream.of(review.getBook(), review.getArticle(), review.getMagazine())
            .filter(Objects::nonNull)
            .count() == 1;
    }
}
