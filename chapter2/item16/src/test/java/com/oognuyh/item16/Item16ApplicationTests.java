package com.oognuyh.item16;

import com.oognuyh.item16.model.Author;
import com.oognuyh.item16.service.AuthorService;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class Item16ApplicationTests {

	@Autowired private AuthorService authorService;

	@Autowired private SessionFactory sessionFactory;

	@Test
	public void test() {
		Statistics statistics = sessionFactory.getStatistics();

		Author author = new Author()
			.id(1L)
			.name("Joana Nimar")
			.age(34)
			.genre("History");

		
		System.out.println("Second Level Cache put count: " + statistics.getSecondLevelCachePutCount());
		System.out.println("Second Level Cache hit count: " + statistics.getSecondLevelCacheHitCount());

		System.out.println("- - - - - - - - - - - - - - - -");

		authorService.createAuthor(author);

		System.out.println("Second Level Cache put count: " + statistics.getSecondLevelCachePutCount());
		System.out.println("Second Level Cache hit count: " + statistics.getSecondLevelCacheHitCount());
		
		System.out.println("- - - - - - - - - - - - - - - -");

		authorService.getAuthorById(1L);

		System.out.println("Second Level Cache put count: " + statistics.getSecondLevelCachePutCount());
		System.out.println("Second Level Cache hit count: " + statistics.getSecondLevelCacheHitCount());

		System.out.println("- - - - - - - - - - - - - - - -");

		authorService.updateAuthor(new Author()
			.id(1L)
			.age(45));

		System.out.println("Second Level Cache put count: " + statistics.getSecondLevelCachePutCount());
		System.out.println("Second Level Cache hit count: " + statistics.getSecondLevelCacheHitCount());

		System.out.println("- - - - - - - - - - - - - - - -");

		authorService.deleteAuthorById(1L);

		System.out.println("Second Level Cache put count: " + statistics.getSecondLevelCachePutCount());
		System.out.println("Second Level Cache hit count: " + statistics.getSecondLevelCacheHitCount());
	}
}
