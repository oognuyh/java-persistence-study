package com.oognuyh.item23.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.oognuyh.item23.model.Author;
import com.oognuyh.item23.repository.AuthorRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    
    private final AuthorRepository authorRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public List<Author> findAllAuthors() {
        return authorRepository.findAll()
            .stream()
            .map(author -> {
                if (author.getAge() < 40) {
                    author.getAvatar();
                } else {
                    author.setAvatar(null);
                }
                
                return author;
            })
            .collect(Collectors.toList());
    }
}
