package com.oognuyh.item12;

import javax.validation.ConstraintViolationException;

import com.oognuyh.item12.model.Review;
import com.oognuyh.item12.repository.BookRepository;
import com.oognuyh.item12.repository.MagazineRepository;
import com.oognuyh.item12.repository.ReviewRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item12ApplicationTests {
	
	@Autowired private ReviewRepository reviewRepository;

	@Autowired private BookRepository bookRepository;

	@Autowired private MagazineRepository magazineRepository;

	@Test
	public void persistReviewOk() {
		Review review = Review.builder()
			.book(bookRepository.getById(1L))
			.content("ok")
			.build();

		Assertions.assertThatNoException()
			.isThrownBy(() -> {
				reviewRepository.save(review);
			});
	}

	@Test
	public void persistReviewWrong() {
		Review review = Review.builder()
			.book(bookRepository.getById(1L))
			.magazine(magazineRepository.getById(1L))
			.content("wrong")
			.build();

		Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
			.isThrownBy(() -> {
				reviewRepository.save(review);
			});
	}
}
