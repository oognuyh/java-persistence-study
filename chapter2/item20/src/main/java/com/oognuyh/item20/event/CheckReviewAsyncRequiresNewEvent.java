package com.oognuyh.item20.event;

import com.oognuyh.item20.model.Review;

public class CheckReviewAsyncRequiresNewEvent extends CheckReviewEvent {

    public CheckReviewAsyncRequiresNewEvent(Review review) {
        super(review);
    }    
}
