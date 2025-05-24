package com.example.UMC8th_MiniProject.web.controller;

import com.example.UMC8th_MiniProject.apiPayload.ApiResponse;
import com.example.UMC8th_MiniProject.converter.TempConverter;
import com.example.UMC8th_MiniProject.service.reviewService.ReviewService;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewFilterRequestDTO;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "latest 탭 강의 최신순 조회 API", description = "latest 탭 강의 조회 API입니다. 기본으로 최신순 조회되며, 파라미터 값으로 옵션 넘겨주세요")
    @GetMapping("/latest")
    public ApiResponse<List<ReviewResponse.SearchReviewResponse>> getLatestReview(@ModelAttribute ReviewFilterRequestDTO filterDto,
                                                                                 @RequestParam(defaultValue = "0") @Min(0) Integer pageNumber){
        List<ReviewResponse.SearchReviewResponse> result = reviewService.reviewFilter(filterDto, pageNumber,1);
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "popular 탭 강의 인기순 조회 API", description = "popular 탭 강의 조회 API입니다. 기본으로 인기순 조회되며, 파라미터 값으로 옵션 넘겨주세요")
    @GetMapping("/popular")
    public ApiResponse<List<ReviewResponse.SearchReviewResponse>> getPopularReview(@ModelAttribute ReviewFilterRequestDTO filterDto,
                                                                                   @RequestParam(defaultValue = "0") @Min(0) Integer pageNumber){
        List<ReviewResponse.SearchReviewResponse> result = reviewService.reviewFilter(filterDto, pageNumber,2);
        return ApiResponse.onSuccess(result);
    }
}
