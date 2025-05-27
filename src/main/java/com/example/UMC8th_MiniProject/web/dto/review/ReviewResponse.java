package com.example.UMC8th_MiniProject.web.dto.review;

import com.example.UMC8th_MiniProject.domain.enums.Platform;
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
        String lectureName;
        BigDecimal rate;
        String teacher;
        StudyTime studyTime;
        Integer likes;
        String content;
        String imageUrl;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LikeResponse {
        Long reviewId;
        Integer currentLikes;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LectureSearchResponse {
        Long lectureId;
        String name;
        String teacher;
        Platform platform;
    }
}
