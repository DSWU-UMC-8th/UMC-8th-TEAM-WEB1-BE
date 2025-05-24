package com.example.UMC8th_MiniProject.web.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class TotalRatingResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getRatingInfoDTO{
        Long lectureId; //강의 Id
        String lectureName; //강의명
        Integer reviewCounts; //리뷰 수
        BigDecimal totalRating; //총 별점
        int[] ratingCounts; // 점수대별 카운트
    }
}
