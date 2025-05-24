package com.example.UMC8th_MiniProject.web.controller;

import com.example.UMC8th_MiniProject.apiPayload.ApiResponse;
import com.example.UMC8th_MiniProject.apiPayload.code.status.ErrorStatus;
import com.example.UMC8th_MiniProject.apiPayload.exception.handler.TempHandler;
import com.example.UMC8th_MiniProject.converter.TotalRatingConverter;
import com.example.UMC8th_MiniProject.domain.Lecture;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.repository.LectureRepository;
import com.example.UMC8th_MiniProject.service.lectureService.LectureService;
import com.example.UMC8th_MiniProject.service.reviewService.ReviewService;
import com.example.UMC8th_MiniProject.web.dto.LectureResponse;
import com.example.UMC8th_MiniProject.web.dto.lecture.TotalRatingResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    private final LectureRepository lectureRepository;

    @Operation(summary = "강의 전체 조회 API", description = "카테고리와 레벨 필터링 없이 모든 강의를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<LectureResponse>> getLectures(
            @Parameter(description = "강의 카테고리 (예: ITPROGRAMMING)")
            @RequestParam(required = false) Category category,
            @Parameter(description = "강의 수준 (예: BEGINNER)")
            @RequestParam(required = false) Level level
    ) {
        List<LectureResponse> result;

        if (category != null && level != null) {
            result = lectureService.getLectureByCategoryAndLevel(category, level);
        } else if (category != null) {
            result = lectureService.getLecturesByCategory(category);
        } else if (level != null) {
            result = lectureService.getLecturesByLevel(level);
        } else {
            result = lectureService.getAllLectures();
        }

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "강의 상세 조회 API", description = "해당되는 ID의 강의 정보를 상세 조회합니다.")
    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureResponse> getLectureById(@PathVariable Long lectureId) {
        LectureResponse lecture = lectureService.getLectureById(lectureId);
        return ResponseEntity.ok(lecture);
    }

    @Operation(summary = "강의 별점 통계 조회 API", description = "해당되는 ID의 강의 별점 통계를 조회합니다.")
    @GetMapping("/{lectureId}/rating")
    public ApiResponse<TotalRatingResponseDTO.getRatingInfoDTO> RatingInfo (@PathVariable(name = "lectureId") Long lectureId){
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new TempHandler(ErrorStatus.LECTURE_NOT_FOUND));
        List<BigDecimal> lectureReviewRatingList = lectureService.getRatingList(lecture);
        if (lectureReviewRatingList.isEmpty()) {
            int[] ratingCounts = {0,0,0,0,0};
            return ApiResponse.onSuccess(TotalRatingConverter.toTotalRatigDTO(lecture,ratingCounts));
        }
        int[] ratingCounts = lectureService.getRatingCounts(lectureReviewRatingList);
        return ApiResponse.onSuccess(TotalRatingConverter.toTotalRatigDTO(lecture,ratingCounts,lectureReviewRatingList));
    }
}
