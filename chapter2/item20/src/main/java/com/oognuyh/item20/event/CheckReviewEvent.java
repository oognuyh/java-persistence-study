package com.oognuyh.item20.event;

import com.oognuyh.item20.model.Review;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CheckReviewEvent {
    
    private final Review review;
}
