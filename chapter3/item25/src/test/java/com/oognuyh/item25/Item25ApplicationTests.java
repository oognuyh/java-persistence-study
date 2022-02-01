package com.oognuyh.item25;

import com.oognuyh.item25.model.Author;
import com.oognuyh.item25.repository.AuthorRepository;
import com.oognuyh.item25.repository.AuthorRepository.AuthorDto;
import com.oognuyh.item25.repository.AuthorRepository.AuthorSummary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
class Item25ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Transactional(readOnly = true)
	@Test
	public void findAuthorSummariesByGenre() {
		Assertions.assertThat(authorRepository.findAuthorSummariesByGenre("Anthology").size()).isGreaterThanOrEqualTo(1);
	}

	@Transactional(readOnly = true)
	@Test
	public void findNameById() {
		Assertions.assertThat(authorRepository.findNameById(1L)).isNotNull();
	}

	@Transactional(readOnly = true)
	@Test
	public void findNameAndAgeById() {
		Assertions.assertThat(authorRepository.findNameAndAgeById(1L)).isNotNull();
	}

	@Transactional(readOnly = true)
	@Test
	public void findAgeAndGenreById() {
		Assertions.assertThat(authorRepository.findAgeAndGenreById(1L)).isNotNull();
	}

	@Transactional(readOnly = true)
	@Test
	public void findDynamicAuthorByName() {
		Assertions.assertThat(authorRepository.findByName("Mark Janel", AuthorDto.class)).isNotNull();
		Assertions.assertThat(authorRepository.findByName("Mark Janel", AuthorSummary.class)).isNotNull();
		Assertions.assertThat(authorRepository.findByName("Mark Janel", Author.class)).isNotNull();
	}
}
