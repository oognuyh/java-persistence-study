package com.oognuyh.item9;

import com.oognuyh.item9.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item9ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Test
	public void showAuthorsAndBooksByGenreAndAge() {
		authorRepository.findByGenreAndAgeGreaterThan("Anthology", 32)
			.forEach(author -> {
				System.out.println(author.getName());
				author.getBooks()
					.forEach(System.out::println);
			});
	}

	@Test
	public void showAuthorsAndBooksByAgeAndGenre() {
		authorRepository.findByAgeGreaterThanAndGenre(32, "Anthology")
			.forEach(author -> {
				System.out.println(author.getName());

				author.getBooks()
					.forEach(System.out::println);
			});
	}
}
