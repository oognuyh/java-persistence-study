package com.oognuyh.item24;

import com.oognuyh.item24.repository.AuthorDeepRepository;
import com.oognuyh.item24.repository.AuthorShallowRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({ InitialData.class })
class Item24ApplicationTests {

	@Autowired private AuthorDeepRepository authorDeepRepository;

	@Autowired private AuthorShallowRepository authorShallowRepository;

	@Test
	public void findAuthorsViaShallow() {
		System.out.println(authorShallowRepository.findAll());
	}

	@Test
	public void findAuthorsViaDeep() {
		System.out.println(authorDeepRepository.findAll());
	}
}
