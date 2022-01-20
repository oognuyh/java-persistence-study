package com.oognuyh.item1;

import com.oognuyh.item1.model.Author;
import com.oognuyh.item1.model.Book;
import com.oognuyh.item1.repository.AuthorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item1ApplicationTests {

    @Autowired AuthorRepository authorRepository;

    @Test
    public void insertAuthorWithBooks() {
        Author author = Author.builder()
            .name("Alicia Tom")
            .age(38)
            .genre("Anthology")
            .build();
        
        author.addBook(Book.builder()
            .isbn("001-AT")
            .title("The book of swords")
            .build());
        
        author.addBook(Book.builder()
            .isbn("002-AT")
            .title("Head Down")
            .build());

        authorRepository.save(author);

        authorRepository.flush();
    }

    @Test
    public void deleteBookOfAuthor() {
        Author author = authorRepository.findByName("Mark Janel")
            .orElseThrow();

        author.removeBook(author.getBooks().get(0));

        authorRepository.flush();
    }

    @Test
    public void deleteAllBookOfAuthor() {
        Author author = authorRepository.findByName("Mark Janel")
            .orElseThrow();

        author.removeAllBook();

        authorRepository.flush();
    }
}
