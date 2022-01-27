package com.oognuyh.item21;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.oognuyh.item21.model.Author;
import com.oognuyh.item21.repository.AuthorRepository;
import com.oognuyh.item21.repository.BookRepository;
import com.oognuyh.item21.specification.InIdSpecification;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@DataJpaTest
class Item21ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@Autowired private BookRepository bookRepository;

	@Autowired private TransactionTemplate transactionTemplate;

	@PersistenceContext EntityManager entityManager;

	@Test
	public void fetchDirectlyViaSpringData() {
		Assertions.assertThat(authorRepository.findById(1L).isPresent()).isTrue();
	}

	@Test
	public void fetchDirectlyViaEntityManager() {
		Assertions.assertThat(Optional.ofNullable(entityManager.find(Author.class, 1L)).isPresent()).isTrue();
	}

	@Test
	public void fetchDirectlyViaHibernateSpecificSession() {
		Session session = entityManager.unwrap(Session.class);

		Assertions.assertThat(Optional.ofNullable(session.get(Author.class, 1L)).isPresent()).isTrue();
	}

	@Test
	public void fetchDirectlyWithSessionLevelRepeatableReads() {
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		transactionTemplate.setIsolationLevel(Isolation.READ_COMMITTED.value());
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Author A1 = authorRepository.findById(1L).orElseThrow();

				System.out.println("Transaction A(findById): " + A1);
				
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {

					@Override
					protected void doInTransactionWithoutResult(TransactionStatus status) {
						Author B = authorRepository.findById(1L).orElseThrow();

						B.setName("Alicia Tom");

						System.out.println("Transaction B(findById + update): " + B);
					}					
				});

				Author A2 = authorRepository.findById(1L).orElseThrow();

				System.out.println("Transaction A(findById): " + A2);

				Author A3 = authorRepository.findByIdViaJpql(1L).orElseThrow();

				System.out.println("Transaction A(findByIdViaJpql): " + A3);

				Author A4 = authorRepository.findByIdViaNativeQuery(1L).orElseThrow();

				System.out.println("Transaction A(findByIdViaNativeQuery): " + A4);

				String A5 = authorRepository.findNameByIdViaJpql(1L).orElseThrow();

				System.out.println("Transaction A(findNameByIdViaJpql): " + A5);

				String A6 = authorRepository.findNameByIdViaNativeQuery(1L).orElseThrow();

				System.out.println("Transaction A(findNameByIdViaNativeQuery): " + A6);
			}
		});
	}

	@Test
	public void fetchDirectlyByMultipleIdsViaFindAllById() {
		System.out.println(bookRepository.findAllById(List.of(1L, 2L, 5L)));
	}

	@Test
	public void fetchDirectlyByMultipleIdsViaJpql() {
		System.out.println(bookRepository.findBooksById(List.of(1L, 2L, 5L)));
	}

	@Test
	public void fetchDirectlyByMultipleIdsViaSpec() {
		System.out.println(bookRepository.findAll(new InIdSpecification(List.of(1L, 2L, 5L))));
	}
}
