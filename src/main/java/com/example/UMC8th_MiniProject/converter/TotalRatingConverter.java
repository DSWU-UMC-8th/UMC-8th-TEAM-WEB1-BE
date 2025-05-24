package com.example.UMC8th_MiniProject.converter;

import com.example.UMC8th_MiniProject.domain.Lecture;
import com.example.UMC8th_MiniProject.web.dto.lecture.TotalRatingResponseDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TotalRatingConverter {

    public static BigDecimal totalRatingcalculator (List<BigDecimal> lectureReviewRatingList){
        BigDecimal totalSum = lectureReviewRatingList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = totalSum.divide(new BigDecimal(lectureReviewRatingList.size()), 2, BigDecimal.ROUND_HALF_UP);
        return average;
    } //평균 구하는 메서드

    public static TotalRatingResponseDTO.getRatingInfoDTO toTotalRatigDTO(Lecture lecture, int[] ratingCounts, List<BigDecimal> lectureReviewRatingList) {

        int totalSum = Arrays.stream(ratingCounts).sum();

        return TotalRatingResponseDTO.getRatingInfoDTO.builder()
                .lectureId(lecture.getLectureId())
                .lectureName(lecture.getName())
                .reviewCounts(totalSum)
                .totalRating(TotalRatingConverter.totalRatingcalculator(lectureReviewRatingList))
                .ratingCounts(ratingCounts)
                .build();
    } // getRatingInfoDTO DTO 만들기

    public static TotalRatingResponseDTO.getRatingInfoDTO toTotalRatigDTO(Lecture lecture, int[] ratingCounts) {

        int totalSum = Arrays.stream(ratingCounts).sum();

        return TotalRatingResponseDTO.getRatingInfoDTO.builder()
                .lectureId(lecture.getLectureId())
                .lectureName(lecture.getName())
                .reviewCounts(totalSum)
                .totalRating(BigDecimal.valueOf(0.00))
                .ratingCounts(ratingCounts)
                .build();
    } // getRatingInfoDTO DTO 만들기
}
