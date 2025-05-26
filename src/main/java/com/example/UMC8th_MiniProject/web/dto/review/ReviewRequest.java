package com.example.UMC8th_MiniProject.web.dto.review;

import com.example.UMC8th_MiniProject.domain.enums.StudyTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class ReviewRequest {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class postReviewDTO{
        Long lectureId;
        BigDecimal rating;
        String content;
        StudyTime studyTime;
    }
}
