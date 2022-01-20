package com.oognuyh.item3;

import java.util.List;

import com.oognuyh.item3.model.Author;
import com.oognuyh.item3.model.Book;
import com.oognuyh.item3.repository.AuthorRepository;
import com.oognuyh.item3.repository.BookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item3ApplicationTests {

	@Autowired private AuthorRepository authorRepository;
	
	@Autowired private BookRepository bookRepository;

	@Test
	public void insertNewBook() {
		Author author = authorRepository.getById(1L);

		Book book = Book.builder()
			.author(author)
			.isbn("003-JN")
			.title("History Of Present")
			.build();

		bookRepository.save(book);

		bookRepository.flush();
	}

	@Test
	public void findByAuthorId() {
		List<Book> books = bookRepository.findByAuthorId(4L);

		Book book = Book.builder()
			.isbn("003-JN")
			.title("History Of Present")
			.author(books.get(0).getAuthor())
			.build();
		
		books.add(bookRepository.save(book));

		bookRepository.flush();
	}

	@Test
	public void findByAuthorIdAndDeleteFirstBook() {
		List<Book> books = bookRepository.findByAuthorId(4L);

		bookRepository.delete(books.remove(0));

		bookRepository.flush();
	}
}
