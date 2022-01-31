package com.oognuyh.item24;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import com.oognuyh.item24.model.AuthorDeep;
import com.oognuyh.item24.repository.AuthorDeepRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialData {
    
    @Autowired AuthorDeepRepository authorDeepRepository;

    @Bean
    public CommandLineRunner setUp() {
        return (args) -> {
            AuthorDeep mt = AuthorDeep.builder()
                .name("Martin Ticher")
                .age(43)
                .genre("Horror")
                .avatar(Files.readAllBytes(new File("avatars/mt.png").toPath()))
                .build();
		
            AuthorDeep cd = AuthorDeep.builder()
                .name("Carla Donnoti")
                .age(31)
                .genre("Science Fiction")
                .avatar(Files.readAllBytes(new File("avatars/cd.png").toPath()))
                .build();

            AuthorDeep re = AuthorDeep.builder()
                .name("Rennata Elibol")
                .age(46)
                .genre("Fantasy")
                .avatar(Files.readAllBytes(new File("avatars/re.png").toPath()))
                .build();

            authorDeepRepository.saveAll(List.of(mt, cd, re));
        };
    }
}
