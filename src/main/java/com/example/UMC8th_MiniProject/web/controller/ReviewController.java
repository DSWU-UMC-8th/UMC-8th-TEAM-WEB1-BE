package com.example.UMC8th_MiniProject.web.controller;

import com.example.UMC8th_MiniProject.apiPayload.ApiResponse;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.domain.enums.StudyTime;
import com.example.UMC8th_MiniProject.service.reviewService.ReviewService;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewFilterRequestDTO;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewResponse;
import com.example.UMC8th_MiniProject.converter.TofilterDtoConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "latest 탭 강의 최신순 조회 API", description = "latest 탭 강의 조회 API입니다. 기본으로 최신순 조회되며, 파라미터 값으로 옵션 넘겨주세요. 페이지는 0부터 시작합니다.")
    @GetMapping("/latest")
    public ApiResponse<List<ReviewResponse.SearchReviewResponse>> getLatestReview(@Parameter(description = "강의 카테고리") @RequestParam(required = false) Category category,
                                                                                  @Parameter(description = "강의 수준") @RequestParam(required = false) Level level,
                                                                                  @Parameter(description = "학습 기간") @RequestParam(required = false) StudyTime studyTime,
                                                                                  @RequestParam(defaultValue = "0") @Min(0) Integer pageNumber){
        ReviewFilterRequestDTO filterDto = TofilterDtoConverter.toReviewFilterRequestDTO(category,level,studyTime);
        List<ReviewResponse.SearchReviewResponse> result = reviewService.reviewFilter(filterDto, pageNumber,1);
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "latest 탭 강의 최신순 조회 API", description = "latest 탭 강의 조회 API입니다. 기본으로 최신순 조회되며, 파라미터 값으로 옵션 넘겨주세요. 페이지는 0부터 시작합니다.")
    @GetMapping("/popular")
    public ApiResponse<List<ReviewResponse.SearchReviewResponse>> getPopularReview(@Parameter(description = "강의 카테고리") @RequestParam(required = false) Category category,
                                                                                  @Parameter(description = "강의 수준") @RequestParam(required = false) Level level,
                                                                                  @Parameter(description = "학습 기간") @RequestParam(required = false) StudyTime studyTime,
                                                                                  @RequestParam(defaultValue = "0") @Min(0) Integer pageNumber){
        ReviewFilterRequestDTO filterDto = TofilterDtoConverter.toReviewFilterRequestDTO(category,level,studyTime);
        List<ReviewResponse.SearchReviewResponse> result = reviewService.reviewFilter(filterDto, pageNumber,2);
        return ApiResponse.onSuccess(result);
    }
}
