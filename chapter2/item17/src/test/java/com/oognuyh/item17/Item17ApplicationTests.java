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
	public void cloneAuthorAndAssociaeBooks() {
		Author author = authorRepository.findByName("Mark Janel");

		Author clonedAuthor = new Author(author, false);

		clonedAuthor.setAge(54);
		clonedAuthor.setName("Farell Tliop");

		authorRepository.saveAndFlush(clonedAuthor);
	}

	@Test
	public void cloneAuthorAndBooks() {
		Author author = authorRepository.findByName("Mark Janel");

		Author clonedAuthor = new Author(author, true);

		clonedAuthor.setAge(54);
		clonedAuthor.setName("Farell Tliop");

		authorRepository.saveAndFlush(clonedAuthor);
	}
}
