package com.oognuyh.item24.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@Table(name = "author")
public class AuthorDeep extends BaseAuthor {
    
    @Lob
    private byte[] avatar;
}
