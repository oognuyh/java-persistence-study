package com.oognuyh.item18;

import com.oognuyh.item18.model.Author;
import com.oognuyh.item18.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item18ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Test
	public void saveAndUpdateAuthor() {
		Author author = Author.builder()
			.name("Joana Nimar")
			.genre("History")
			.age(34)
			.build();

		authorRepository.save(author);

		author.setAge(35);
		
		authorRepository.flush();
	}
}
