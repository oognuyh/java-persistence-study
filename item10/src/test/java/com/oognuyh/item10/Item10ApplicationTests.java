package com.oognuyh.item10;

import com.oognuyh.item10.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item10ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Test
	public void showAuthorAndBooksByPriceGreaterThan20() {
		authorRepository.findById(1L)
			.ifPresent(author -> System.out.println(author.getBooks()));
	}
}
