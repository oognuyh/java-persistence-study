package com.oognuyh.item17;

import com.oognuyh.item17.model.Author;
import com.oognuyh.item17.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item17ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Test
	public void cloneAuthorWithoutBooks() {
		Author author = authorRepository.findByName("Mark Janel");

		Author clonedAuthor = new Author(author, false);

		clonedAuthor.setAge(54);
		clonedAuthor.setName("Farell Tliop");

		authorRepository.save(clonedAuthor).getBooks().forEach(book -> System.out.println(book.getAuthors()));
	}

	@Test
	public void cloneAuthorWithBooks() {
		Author author = authorRepository.findByName("Mark Janel");

		Author clonedAuthor = new Author(author, true);

		clonedAuthor.setAge(54);
		clonedAuthor.setName("Farell Tliop");

		authorRepository.save(clonedAuthor);
	}
}
