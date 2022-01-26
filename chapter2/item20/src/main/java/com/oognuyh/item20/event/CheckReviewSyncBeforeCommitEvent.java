package com.oognuyh.item20.event;

import com.oognuyh.item20.model.Review;

public class CheckReviewSyncBeforeCommitEvent extends CheckReviewEvent {

    public CheckReviewSyncBeforeCommitEvent(Review review) {
        super(review);
    }
}
