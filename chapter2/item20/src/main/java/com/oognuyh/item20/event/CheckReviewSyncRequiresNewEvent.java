package com.oognuyh.item20.event;

import com.oognuyh.item20.model.Review;

public class CheckReviewSyncRequiresNewEvent extends CheckReviewEvent {

    public CheckReviewSyncRequiresNewEvent(Review review) {
        super(review);
    }    
}
