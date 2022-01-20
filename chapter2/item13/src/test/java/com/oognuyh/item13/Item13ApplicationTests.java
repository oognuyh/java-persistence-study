package com.oognuyh.item13;

import com.oognuyh.item13.model.Author;
import com.oognuyh.item13.model.Book;
import com.oognuyh.item13.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item13ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Test
	void persistAuthorWithBooks() {
		Author author = new Author()
			.name("Joana Nimar")
			.age(32)
			.genre("History")
			.addBook(new Book()
				.setTitle("A History of Ancient Prague")
				.setIsbn("001-JN"))
			.addBook(new Book()
				.setTitle("A People's History")
				.setIsbn("002-JN"));
		
		authorRepository.save(author);
	}
}
