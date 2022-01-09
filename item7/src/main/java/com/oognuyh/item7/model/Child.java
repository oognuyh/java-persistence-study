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
@Entity(name = "child")
public class Child implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    private String name;

    private Integer age;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    Parent parent;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (Objects.isNull(obj)) return false;
        if (getClass() != obj.getClass()) return false;

        return Objects.nonNull(id) && id.equals(((Child) obj).id);
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
