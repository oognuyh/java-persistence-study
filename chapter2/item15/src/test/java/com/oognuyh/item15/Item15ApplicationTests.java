package com.oognuyh.item15;

import java.util.Optional;

import com.oognuyh.item15.model.Author;
import com.oognuyh.item15.model.Book;
import com.oognuyh.item15.repository.AuthorRepository;
import com.oognuyh.item15.repository.BookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item15ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Autowired private BookRepository bookRepository;

	@Test
	void getAuthorById() {
		Optional<Author> author = authorRepository.findById(1L);

		author.ifPresent(System.out::println);
	}

	@Test
	void getAuthorFromBook() {
		Optional<Book> book = bookRepository.findByTitle("Mastering JSF 2.2");

		book.ifPresent(b -> {
			b.title().ifPresent(System.out::println);
			b.author().ifPresent(System.out::println);
		});
	}
}
