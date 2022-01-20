package com.oognuyh.item5.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

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
@Entity(name = "author_set")
public class AuthorSet implements Serializable {

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
        name = "author_book_set",
        joinColumns = @JoinColumn(name = "authorId"),
        inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private Set<BookSet> books = new HashSet<>();

    public void addBook(BookSet book) {
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(BookSet book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
    }

    public void removeBooks() {
        Iterator<BookSet> iterator = this.books.iterator();

        while (iterator.hasNext()) {
            BookSet book = iterator.next();
            book.getAuthors().remove(this);
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (Objects.isNull(obj)) return false;
        if (getClass() != obj.getClass()) return false;

        return Objects.nonNull(id) && id.equals(((AuthorSet) obj).id);
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
