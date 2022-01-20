package com.oognuyh.item5.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
@Entity(name = "author_list")
public class AuthorList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private String genre;

    @Builder.Default
    @ToString.Exclude
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })    
    @JoinTable(
        name = "author_book_list",
        joinColumns = @JoinColumn(name = "authorId"),
        inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private List<BookList> books = new ArrayList<>();

    public void addBook(BookList book) {
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(BookList book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
    }

    public void removeBooks() {
        Iterator<BookList> iterator = this.books.iterator();

        while (iterator.hasNext()) {
            BookList book = iterator.next();
            book.getAuthors().remove(this);
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (Objects.isNull(obj)) return false;
        if (getClass() != obj.getClass()) return false;

        return Objects.nonNull(id) && id.equals(((AuthorList) obj).id);
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
