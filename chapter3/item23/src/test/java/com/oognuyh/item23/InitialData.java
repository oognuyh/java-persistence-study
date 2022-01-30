package com.oognuyh.item23;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import com.oognuyh.item23.model.Author;
import com.oognuyh.item23.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialData {
    
    @Autowired AuthorRepository authorRepository;

    @Bean
    public CommandLineRunner setUp() {
        return (args) -> {
            Author mt = Author.builder()
                .name("Martin Ticher")
                .age(43)
                .genre("Horror")
                .avatar(Files.readAllBytes(new File("avatars/mt.png").toPath()))
                .build();
		
            Author cd = Author.builder()
                .name("Carla Donnoti")
                .age(31)
                .genre("Science Fiction")
                .avatar(Files.readAllBytes(new File("avatars/cd.png").toPath()))
                .build();

            Author re = Author.builder()
                .name("Rennata Elibol")
                .age(46)
                .genre("Fantasy")
                .avatar(Files.readAllBytes(new File("avatars/re.png").toPath()))
                .build();

            authorRepository.saveAll(List.of(mt, cd, re));
        };
    }
}
