package com.oognuyh.item23;

import com.oognuyh.item23.dto.AuthorDto;
import com.oognuyh.item23.model.Author;
import com.oognuyh.item23.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@Import({ InitialData.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.jpa.show-sql = true")
class Item23ApplicationTests {

	@Autowired AuthorRepository authorRepository;

	@Autowired private TestRestTemplate restTemplate;

	@Transactional(readOnly = true)
	@Test
	public void findAuthorsByAgeGreaterThanEqual() {
		// N + 1 
		authorRepository.findByAgeGreaterThanEqual(35)
			.forEach(author -> System.out.println(author.getAvatar().length));
	}

	@Transactional(readOnly = true)
	@Test
	public void findAuthorDtosByAgeGreaterThanEqual() {
		authorRepository.findAuthorDtosByAgeGreaterThanEqual(35)
			.forEach(AuthorDto::getAvatar);
	}

	@Test
	public void findAllAuthors() {
		Author[] authors = restTemplate
			.getForObject("/authors", Author[].class);

		System.out.println(authors);
	}
}
