package com.oognuyh.item9.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import com.oognuyh.item9.repository.AuthorRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "author")
@NamedEntityGraph(
    name = AuthorRepository.AUTHOR_BOOKS_GRAPH,
    attributeNodes = {
        @NamedAttributeNode(value = "books"),
        @NamedAttributeNode(value = "name")
    }
)
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    private Integer age;

    @ToString.Exclude
    @Basic(fetch = FetchType.LAZY)
    private String genre;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        book.setAuthor(null);
        this.books.remove(book);
    }

    public void removeAllBook() {
        Iterator<Book> iterator = this.books.iterator();
        
        while (iterator.hasNext()) {
            Book book = iterator.next();
            book.setAuthor(null);
            iterator.remove();
        }
    }
}

