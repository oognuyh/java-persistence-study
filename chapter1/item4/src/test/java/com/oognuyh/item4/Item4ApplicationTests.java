package com.oognuyh.item4;

import com.oognuyh.item4.model.Author;
import com.oognuyh.item4.model.Book;
import com.oognuyh.item4.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item4ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Test
	public void insertAuthorsWithBooks() {
		Author alicia = Author.builder()
			.name("Alicia Tom")
			.age(38)
			.genre("Anthology")
			.build();

		Author mark = Author.builder()
			.name("Mark Janel")
			.age(23)
			.genre("Anthology")
			.build();

		Book bookOfSwords = Book.builder()
			.isbn("001-AT-MJ")
			.title("The book of swords")
			.build();

		Book oneDay = Book.builder()
			.isbn("002-AT-MJ")
			.title("One Day")
			.build();

		alicia.addBook(bookOfSwords);
		mark.addBook(oneDay);

		authorRepository.save(alicia);
		authorRepository.save(mark);

		authorRepository.flush();
	}
}
