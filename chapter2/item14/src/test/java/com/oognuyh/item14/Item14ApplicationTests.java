package com.oognuyh.item14;

import com.oognuyh.item14.model.Author;
import com.oognuyh.item14.model.Book;
import com.oognuyh.item14.repository.AuthorRepository;
import com.oognuyh.item14.repository.BookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item14ApplicationTests {

	@Autowired private AuthorRepository authorRepository;
	
	@Autowired private BookRepository bookRepository;

	@Test
	void persistBookToAuthorWithEntity() {
		Author author = authorRepository.findById(1L).orElseThrow();

		Book book = new Book()
			.isbn("001-MJ")
			.title("The Canterbury Anthology")
			.author(author);

		bookRepository.save(book);
	}

	@Test
	void persistBookToAuthorWithProxy() {
		Author author = authorRepository.getById(1L);

		Book book = new Book()
			.isbn("001-MJ")
			.title("The Canterbury Anthology")
			.author(author);

		bookRepository.save(book);
	}
}
