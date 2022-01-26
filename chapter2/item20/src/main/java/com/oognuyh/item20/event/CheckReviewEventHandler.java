package com.oognuyh.item20.event;

import java.util.Random;

import com.oognuyh.item20.model.Review;
import com.oognuyh.item20.model.Review.Status;
import com.oognuyh.item20.repository.ReviewRepository;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckReviewEventHandler {
    
    private final ReviewRepository reviewRepository;

    @Async
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(CheckReviewAsyncRequiresNewEvent event) {
        process(event);
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(CheckReviewSyncRequiresNewEvent event) {
        process(event);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @Transactional
    public void handle(CheckReviewSyncBeforeCommitEvent event) {
        process(event);
    }

    public void process(CheckReviewEvent event) {
        log.info("Running on thread({})", Thread.currentThread().getId());
        
        Review review = event.getReview();
        
        log.info("Received event with review({})", review);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());

            Thread.currentThread().interrupt();
        }

        review.setStatus(new Random().nextBoolean() ? 
                            Status.ACCEPT : Status.REJECT);
        
        reviewRepository.saveAndFlush(review);

        log.info("Review({}) has been checked", review);
    }
}
