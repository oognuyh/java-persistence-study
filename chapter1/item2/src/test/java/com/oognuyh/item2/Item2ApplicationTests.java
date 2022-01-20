package com.oognuyh.item2;

import com.oognuyh.item2.model.Author;
import com.oognuyh.item2.model.Book;
import com.oognuyh.item2.repository.AuthorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item2ApplicationTests {

    @Autowired AuthorRepository authorRepository;

    @BeforeEach
    public void insertAuthorWithBooks() {
        Author author = Author.builder()
            .name("Joana Nimar")
            .age(34)
            .genre("History")
            .build();

        
        author.addBook(Book.builder()
            .isbn("001-JN")
            .title("A History of Ancient Prague")
            .build());
        
        author.addBook(Book.builder()
            .isbn("002-JN")
            .title("A People's History")
            .build());

        authorRepository.save(author);

        authorRepository.flush();
    }

    @Test
    public void insertNewBook() {
        Author author = authorRepository.findByName("Joana Nimar")
            .orElseThrow();

        author.addBook(Book.builder()
            .isbn("003-JN")
            .title("History Details")
            .build());

        authorRepository.save(author);

        authorRepository.flush();
    }

    @Test
    public void deleteFirstBook() {
        Author author = authorRepository.findByName("Joana Nimar")
            .orElseThrow();
        
        author.removeBook(author.getBooks().get(0));
        
        authorRepository.flush();
    }

    @Test
    public void deleteLastBook() {
        Author author = authorRepository.findByName("Joana Nimar")
            .orElseThrow();
        
        author.removeBook(author.getBooks().get(author.getBooks().size() - 1));
        
        authorRepository.flush();
    }
}
