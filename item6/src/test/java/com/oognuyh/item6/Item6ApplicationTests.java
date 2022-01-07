package com.oognuyh.item6;

import java.util.List;

import com.oognuyh.item6.model.Author;
import com.oognuyh.item6.repository.AuthorRepository;
import com.oognuyh.item6.repository.BookRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class Item6ApplicationTests {

    @Autowired private AuthorRepository authorRepository;

    @Autowired private BookRepository bookRepository;

    @AfterEach
    public void flush() {
        authorRepository.flush();
    }

    @Test
    public void deleteViaCascadeRemove() {
        Author author = authorRepository.findByName("Joana Nimar");

        authorRepository.delete(author);
    }

    @Test
    public void deleteViaOrphanRemoval() {
        Author author = authorRepository.findByNameWithBooks("Joana Nimar");

        author.removeAllBook();
        authorRepository.delete(author);
    }

    @Test
    public void oneAuthorLoaded() {
        Author author = authorRepository.findByName("Joana Nimar");

        bookRepository.deleteByAuthorId(author.getId());
        authorRepository.deleteById(author.getId());
    }

    @Test
    public void oneAuthorAndBooksLoaded() {
        Author author = authorRepository.findByNameWithBooks("Joana Nimar");

        bookRepository.deleteByAuthorId(author.getId());
        authorRepository.deleteById(author.getId());
    }

    @Test
    public void MultipleAuthorLoaded() {
        List<Author> authors = authorRepository.findByAge(34);

        bookRepository.deleteByAuthors(authors);
        authorRepository.deleteAllInBatch(authors);
    }

    @Test
    public void MultipleAuthorAndBooksLoaded() {
        List<Author> authors = authorRepository.findByGenreWithBooks("Anthology");

        bookRepository.deleteByAuthors(authors);
        authorRepository.deleteAllInBatch(authors);
    }

    @Test
    public void NoOneLoaded() {
        bookRepository.deleteByAuthorId(4L);
        authorRepository.deleteById(4L);
    }

    @Test
    public void NoOneLoadedAndDoViaBulk() {
        List<Long> ids = List.of(1L, 4L);

        bookRepository.deleteByAuthorIds(ids);
        authorRepository.deleteByIds(ids);
    }
}
