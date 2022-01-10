package com.oognuyh.item8;

import com.oognuyh.item8.repository.AuthorRepository;
import com.oognuyh.item8.repository.PublisherRepository;
import com.oognuyh.item8.spec.AuthorSpecs;
import com.oognuyh.item8.spec.PublisherSpecs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item8ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Autowired private PublisherRepository publisherRepository;

	@Test
	public void showAuthorsWithBooksAndPublishers() {
		authorRepository.findAll()
			.forEach(author -> {
				System.out.println(author);

				author.getBooks()
					.forEach(book -> System.out.println(book + " " + book.getPublisher()));
			});
	}

	@Test
	public void showAuthorsByAgeWithBooksAndPublishers() {
		authorRepository.findByAgeLessThanOrderByNameDesc(43)
			.forEach(author -> {
				System.out.println(author);

				author.getBooks()
					.forEach(book -> System.out.println(book + " " + book.getPublisher()));
			});
	}

	@Test
	public void showAuthorsByAgeBetween20And40WithBooksAndPublishers() {
		authorRepository.findByAgeBetween20And40()
			.forEach(author -> {
				System.out.println(author);

				author.getBooks()
					.forEach(book -> System.out.println(book + " " + book.getPublisher()));
			});
	}
	
	@Test
	public void showAuthorsWithBooksAndPublishersWithSpec() {
        authorRepository.findAll(AuthorSpecs.isAgeGreaterThan(33))
			.forEach(author -> {
				System.out.println(author);

				author.getBooks()
					.forEach(book -> System.out.println(book + " " + book.getPublisher()));
			});
    }

	@Test
    public void showPublishersWithBooksAndAuthors() {
        publisherRepository.findAll()
			.forEach(publisher -> {
				System.out.println(publisher);

				publisher.getBooks()
					.forEach(book -> System.out.println(book + " " + book.getAuthor()));
			});
    }

	@Test
    public void showPublishersByIdWithBooksAndAuthors() {
        publisherRepository.findByIdLessThanOrderByCompanyDesc(2L)
			.forEach(publisher -> {
				System.out.println(publisher);

				publisher.getBooks()
					.forEach(book -> System.out.println(book + " " + book.getAuthor()));
			});
    }

	@Test
    public void showPublishersWithBooksAndAuthorsWithSpec() {
        publisherRepository.findAll(PublisherSpecs.isIdGreaterThan(2L))
			.forEach(publisher -> {
				System.out.println(publisher);

				publisher.getBooks()
					.forEach(book -> System.out.println(book + " " + book.getAuthor()));
			});
    }

	@Test
    public void showPublishersByIdBetween1And3WithBooksAndAuthors() {
        publisherRepository.findByIdBetween1And3()
			.forEach(publisher -> {
				System.out.println(publisher);

				publisher.getBooks()
					.forEach(book -> System.out.println(book + " " + book.getAuthor()));
			});
    }
}
