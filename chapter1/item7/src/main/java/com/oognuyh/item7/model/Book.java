package com.oognuyh.item7.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import com.oognuyh.item7.repository.BookRepository;

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
@Entity(name = "book")
@NamedEntityGraph(
    name = BookRepository.BOOK_AUTHOR_GRAPH,
    attributeNodes = {
        @NamedAttributeNode("author")
    }
)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private String title;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId")
    private Author author;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (Objects.isNull(obj)) return false;
        if (getClass() != obj.getClass()) return false;

        return Objects.nonNull(id) && id.equals(((Book) obj).id);
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
