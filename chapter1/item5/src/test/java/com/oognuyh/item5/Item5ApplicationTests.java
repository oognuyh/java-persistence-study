package com.oognuyh.item5;

import com.oognuyh.item5.model.AuthorList;
import com.oognuyh.item5.model.AuthorSet;
import com.oognuyh.item5.model.BookList;
import com.oognuyh.item5.model.BookSet;
import com.oognuyh.item5.repository.AuthorListRepository;
import com.oognuyh.item5.repository.AuthorSetRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item5ApplicationTests {

    @Autowired private AuthorListRepository authorListRepository;

    @Autowired private AuthorSetRepository authorSetRepository;

    @Test
    public void persistAuthorWithBOoksAndRemoveOneBookList() {
        AuthorList alicia = AuthorList.builder()
            .name("Alicia Tom")
            .age(38)
            .genre("Anthology")
            .build();

        AuthorList mark = AuthorList.builder()
            .name("Mark Janel")
            .age(23)
            .genre("Anthology")
            .build();

        BookList bookOfSwords = BookList.builder()
            .isbn("001-AT-MJ")
            .title("The book of swords")
            .build();

        BookList oneDay = BookList.builder()
            .isbn("002-AT-MJ")
            .title("One Day")
            .build();

        BookList headDown = BookList.builder()
            .isbn("001-AT")
            .title("Head Down")
            .build();

        alicia.addBook(bookOfSwords);
        mark.addBook(bookOfSwords);
        alicia.addBook(oneDay);
        mark.addBook(oneDay);
        alicia.addBook(headDown);

        authorListRepository.save(alicia);
        authorListRepository.saveAndFlush(mark);

        alicia.removeBook(oneDay);

        authorListRepository.flush();
    }

    @Test
    public void persistAuthorWithBooksAndRemoveOneBookSet() {
        AuthorSet alicia = AuthorSet.builder()
            .name("Alicia Tom")
            .age(38)
            .genre("Anthology")
            .build();

        AuthorSet mark = AuthorSet.builder()
            .name("Mark Janel")
            .age(23)
            .genre("Anthology")
            .build();

        BookSet bookOfSwords = BookSet.builder()
            .isbn("001-AT-MJ")
            .title("The book of swords")
            .build();

        BookSet oneDay = BookSet.builder()
            .isbn("002-AT-MJ")
            .title("One Day")
            .build();

        BookSet headDown = BookSet.builder()
            .isbn("001-AT")
            .title("Head Down")
            .build();

        alicia.addBook(bookOfSwords);
        mark.addBook(bookOfSwords);
        alicia.addBook(oneDay);
        mark.addBook(oneDay);
        alicia.addBook(headDown);

        authorSetRepository.save(alicia);
        authorSetRepository.saveAndFlush(mark);

        alicia.removeBook(oneDay);

        authorSetRepository.flush();
    }
}
