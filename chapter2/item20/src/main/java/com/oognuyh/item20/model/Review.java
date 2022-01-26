package com.oognuyh.item20.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.oognuyh.item20.event.CheckReviewAsyncRequiresNewEvent;
import com.oognuyh.item20.event.CheckReviewSyncBeforeCommitEvent;
import com.oognuyh.item20.event.CheckReviewSyncRequiresNewEvent;

import org.springframework.data.domain.AbstractAggregateRoot;

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
@Entity(name = "review")
public class Review extends AbstractAggregateRoot<Review> implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Status {
        CHECK, ACCEPT, REJECT
    }

    public enum Type {
        ASYNC_REQUIRES_NEW, SYNC_REQUIRES_NEW, SYNC_BEFORE_COMMIT
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book;

    public void registerReviewEvent(Type type) {
        switch (type) {
            case ASYNC_REQUIRES_NEW:
                registerEvent(new CheckReviewAsyncRequiresNewEvent(this));
                break;
            case SYNC_BEFORE_COMMIT:
            registerEvent(new CheckReviewSyncRequiresNewEvent(this));
                break;
            case SYNC_REQUIRES_NEW:
                registerEvent(new CheckReviewSyncBeforeCommitEvent(this));
                break;
            default:
                break;
        }
    }

    @Override
    public int hashCode() {
        return 42;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (Objects.isNull(obj) || !(obj instanceof Review)) return false;

        return Objects.nonNull(this.id) && this.id.equals(((Review) obj).id);
    }
}
