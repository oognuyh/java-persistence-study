package com.oognuyh.item15.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book")
@Accessors(fluent = true, chain = true)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String isbn;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId")
    private Author author;

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

    public Optional<String> title() {
        return Optional.ofNullable(title);
    }

    public Optional<Author> author() {
        return Optional.ofNullable(author);
    }
}
