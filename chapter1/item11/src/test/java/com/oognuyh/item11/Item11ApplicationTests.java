package com.oognuyh.item11;

import com.oognuyh.item11.repository.AuthorRepository;
import com.oognuyh.item11.repository.BookRepository;
import com.oognuyh.item11.repository.ParentRepository;
import com.oognuyh.item11.repository.StudentRepository;
import com.oognuyh.item11.repository.TeacherRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item11ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Autowired private BookRepository bookRepository;

	@Autowired private TeacherRepository teacherRepository;

	@Autowired private StudentRepository studentRepository;

	@Autowired private ParentRepository parentRepository;

	/**
	 * UniDirectional
	 */
	@Test
	public void findStudentByTeacher() {
		teacherRepository.findById(1L).ifPresent(studentRepository::findByTeacher);
	}

	/**
	 * BiDirectional
	 */
	@Test
	public void findParent() {
		parentRepository.findById(1L);
	}

	/**
	 * @MapsId
	 */
	@Test
	public void findAuthorAndBook() {
		authorRepository.findById(1L).ifPresent(author -> {
			bookRepository.findById(author.getId());
		});
	}
}
