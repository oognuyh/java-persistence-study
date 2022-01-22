package com.oognuyh.item16.service;

import java.util.Optional;

import com.oognuyh.item16.model.Author;
import com.oognuyh.item16.repository.AuthorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {
    
    private final AuthorRepository authorRepository;

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Transactional
    public void updateAuthor(Author author) {
        Author existing = authorRepository.findById(author.id())
            .orElseThrow();
        
        existing.age(author.age());
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
