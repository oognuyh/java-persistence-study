package com.oognuyh.item7;

import com.oognuyh.item7.repository.AuthorRepository;
import com.oognuyh.item7.repository.BookRepository;
import com.oognuyh.item7.repository.ChildRepository;
import com.oognuyh.item7.repository.ParentRepository;
import com.oognuyh.item7.spec.AuthorSpecs;
import com.oognuyh.item7.spec.ParentSpecs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item7ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Autowired private BookRepository bookRepository; 

	@Autowired private ParentRepository parentRepository;

	@Autowired private ChildRepository childRepository;

	/**
	 * Using @NamedEntityGraph
	 */
	@Test
	public void showAuthorsAndBooks() {
		authorRepository.findAll()
			.forEach(author -> System.out.println(author + " " + author.getBooks()));
	}

	@Test
	public void showAuthorsAndBooksByAge() {
		authorRepository.findByAgeLessThanOrderByNameDesc(20)
			.forEach(author -> System.out.println(author + " " + author.getBooks()));
	}

	@Test
	public void showAuthorsAndBooksByAgeWithSpec() {
		authorRepository.findAll(AuthorSpecs.isAgeGreaterThan(45))
			.forEach(author -> System.out.println(author + " " + author.getBooks()));
	}

	@Test
	public void showAuthorsAndBooksByAgeBetween20And40() {
		authorRepository.findByAgeBetween20And40()
			.forEach(author -> System.out.println(author + " " + author.getBooks()));
	}

	@Test
	public void showBooksAndAuthors() {
		bookRepository.findAll()
			.forEach(book -> System.out.println(book + " " + book.getAuthor()));
	}

	/**
	 * Using Ad Hoc 
	 */
	@Test
	public void showParentsAndChildren() {
		parentRepository.findAll()
			.forEach(parent -> System.out.println(parent + " " + parent.getChildren()));
	}

	@Test
	public void showParentsAndChildrenByAge() {
		parentRepository.findByAgeLessThanOrderByNameDesc(24)
			.forEach(parent -> System.out.println(parent + " " + parent.getChildren()));
	}

	@Test
	public void showParentsAndChildrenByAgeWithSpec() {
		parentRepository.findAll(ParentSpecs.isAgeGreaterThan(43))
			.forEach(parent -> System.out.println(parent + " " + parent.getChildren()));
	}

	@Test
	public void showParentsAndChildrenByAgeBetween20And40() {
		parentRepository.findByAgeBetween20And40()
			.forEach(parent -> System.out.println(parent + " " + parent.getChildren()));
	}

	@Test
	public void showChildrenAndParent() {
		childRepository.findAll()
			.forEach(child -> System.out.println(child + " " + child.getParent()));
	}
}
