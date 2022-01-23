package com.oognuyh.item17.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;

    @ToString.Exclude
    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    public Book(Book book) {
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
    }

    @Override
    public int hashCode() {
        return 42;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (Objects.isNull(obj)) return false;
        if (obj.getClass() != this.getClass()) return false;

        return Objects.nonNull(this.id) && this.id.equals(((Book) obj).id);
    }
}
