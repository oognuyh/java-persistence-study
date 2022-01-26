package com.oognuyh.item20;

import com.oognuyh.item20.model.Book;
import com.oognuyh.item20.model.Review;
import com.oognuyh.item20.model.Review.Status;
import com.oognuyh.item20.model.Review.Type;
import com.oognuyh.item20.repository.BookRepository;
import com.oognuyh.item20.repository.ReviewRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class Item20ApplicationTests {

	@Autowired private BookRepository bookRepository;

	@Autowired private ReviewRepository reviewRepository;

	@Test
	public void postReviewWithAsyncRequiresNew() {
		log.info("Test running on thread({})", Thread.currentThread().getId());

		Book book = bookRepository.getById(1L);

		Review review = Review.builder()
			.status(Status.CHECK)
			.email("email@email.ema")
			.content("content")
			.book(book)
			.build();
		
		review.registerReviewEvent(Type.ASYNC_REQUIRES_NEW);

		System.out.println(reviewRepository.saveAndFlush(review));

		hold();
	}

	@Test
	public void postReviewWithSyncRequiresNew() {
		Book book = bookRepository.getById(1L);

		Review review = Review.builder()
			.status(Status.CHECK)
			.email("email@email.ema")
			.content("content")
			.book(book)
			.build();
		
		review.registerReviewEvent(Type.SYNC_REQUIRES_NEW);

		System.out.println(reviewRepository.saveAndFlush(review));
	}

	@Test
	public void postReviewWithSyncBeforeCommit() {
		Book book = bookRepository.getById(1L);

		Review review = Review.builder()
			.status(Status.CHECK)
			.email("email@email.ema")
			.content("content")
			.book(book)
			.build();
		
		review.registerReviewEvent(Type.SYNC_BEFORE_COMMIT);

		System.out.println(reviewRepository.saveAndFlush(review));
	}

	public void hold() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
