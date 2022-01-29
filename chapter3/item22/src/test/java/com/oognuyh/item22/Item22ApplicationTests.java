package com.oognuyh.item22;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.oognuyh.item22.repository.AuthorRepository;

import org.assertj.core.api.Assertions;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
class Item22ApplicationTests {

	@Autowired private AuthorRepository authorRepository;

	@PersistenceContext private EntityManager entityManager;

	@Test
	@Transactional(readOnly = true)
	public void findAuthorByNameViaReadOnlyMode() {
		Assertions
			.assertThat(authorRepository
				.findByName("Joana Nimar")
				.isPresent())
			.isTrue();

		briefOverviewOfPersistenceContext();
	}

	@Test
	public void findAuthorByNameViaReadWriteMode() {
		Assertions
			.assertThat(authorRepository
				.findByName("Joana Nimar")
				.isPresent())
			.isTrue();

		briefOverviewOfPersistenceContext();
	}

	public void briefOverviewOfPersistenceContext() {
		SharedSessionContractImplementor sharedSession = entityManager.unwrap(SharedSessionContractImplementor.class);
		org.hibernate.engine.spi.PersistenceContext persistenceContext = sharedSession.getPersistenceContext();

        System.out.println("\n-----------------------------------");
        System.out.println("Total number of managed entities: " + persistenceContext.getNumberOfManagedEntities());
        System.out.println("Total number of collection entries: " + persistenceContext.getCollectionEntriesSize());

        for (var entry : persistenceContext.reentrantSafeEntityEntries()) {
			System.out.println(
                    "Entity name: " + entry.getValue().getEntityName()
                    + " | Status: " + entry.getValue().getStatus()
                    + " | State: " + Arrays.toString(entry.getValue().getLoadedState()));
		}

        System.out.println("-----------------------------------\n");
	}
}
