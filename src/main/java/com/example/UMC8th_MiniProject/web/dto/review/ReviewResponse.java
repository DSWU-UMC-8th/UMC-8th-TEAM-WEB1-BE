package com.example.UMC8th_MiniProject.web.dto.review;

import com.example.UMC8th_MiniProject.domain.enums.StudyTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReviewResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SearchReviewResponse{
        Long reviewId;
        BigDecimal rate;
        StudyTime studyTime;
        Integer likes;
        String content;
        String imageUrl;
        LocalDateTime createdAt;
    }
}
