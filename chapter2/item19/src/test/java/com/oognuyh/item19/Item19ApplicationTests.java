package com.oognuyh.item19;

import com.oognuyh.item19.model.Author;
import com.oognuyh.item19.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item19ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Test
	public void persistAuthor() {
		Author author = Author.builder()
			.age(43)
			.name("Alicia Weys")
			.genre("Horror")
			.bestSelling(true)
			.build();

		authorRepository.saveAndFlush(author);
	}

	@Test
	public void findAuthor() {
		authorRepository.findById(1L)
			.ifPresent(System.out::println);
	}
}
