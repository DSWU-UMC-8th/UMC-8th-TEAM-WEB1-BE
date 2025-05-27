package com.example.UMC8th_MiniProject.web.controller;

import com.example.UMC8th_MiniProject.apiPayload.ApiResponse;
import com.example.UMC8th_MiniProject.converter.TofilterDtoConverter;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.domain.enums.StudyTime;
import com.example.UMC8th_MiniProject.service.reviewService.ReviewSearchService;
import com.example.UMC8th_MiniProject.service.reviewService.ReviewService;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewFilterRequestDTO;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewRequest;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewSearchService reviewSearchService;

    @Operation(summary = "latest 탭 강의평 최신순 조회 API", description = "latest 탭 강의평 조회 API입니다. 기본으로 최신순 조회되며, 파라미터 값으로 옵션 넘겨주세요. 페이지는 0부터 시작합니다.")
    @GetMapping("/latest")
    public ApiResponse<List<ReviewResponse.SearchReviewResponse>> getLatestReview(@Parameter(description = "강의 카테고리") @RequestParam(required = false) Category category,
                                                                                  @Parameter(description = "강의 수준") @RequestParam(required = false) Level level,
                                                                                  @Parameter(description = "학습 기간") @RequestParam(required = false) StudyTime studyTime,
                                                                                  @RequestParam(defaultValue = "0") @Min(0) Integer pageNumber){
        ReviewFilterRequestDTO filterDto = TofilterDtoConverter.toReviewFilterRequestDTO(category,level,studyTime);
        List<ReviewResponse.SearchReviewResponse> result = reviewService.reviewFilter(filterDto, pageNumber,1);
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "popular 탭 강의평 인기순 조회 API", description = "popular 탭 강의평 조회 API입니다. 기본으로 인기순 조회되며, 파라미터 값으로 옵션 넘겨주세요. 페이지는 0부터 시작합니다.")
    @GetMapping("/popular")
    public ApiResponse<List<ReviewResponse.SearchReviewResponse>> getPopularReview(@Parameter(description = "강의 카테고리") @RequestParam(required = false) Category category,
                                                                                  @Parameter(description = "강의 수준") @RequestParam(required = false) Level level,
                                                                                  @Parameter(description = "학습 기간") @RequestParam(required = false) StudyTime studyTime,
                                                                                  @RequestParam(defaultValue = "0") @Min(0) Integer pageNumber){
        ReviewFilterRequestDTO filterDto = TofilterDtoConverter.toReviewFilterRequestDTO(category,level,studyTime);
        List<ReviewResponse.SearchReviewResponse> result = reviewService.reviewFilter(filterDto, pageNumber,2);
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "강의평 좋아요 API", description = "특정 강의평에 좋아요를 누릅니다.")
    @PostMapping("/{reviewId}/like")
    public ApiResponse<ReviewResponse.LikeResponse> likeReview(
            @PathVariable Long reviewId) {

        ReviewResponse.LikeResponse result = reviewService.increaseLikes(reviewId);
        return ApiResponse.onSuccess(result);
    }


    @Operation(summary = "강의평 등록 시 강의 검색 API", description = "강의평 등록 시 입력할 강의를 검색합니다. lectureId, name, teacher, platform을 반환합니다.")
    @GetMapping("/lecture/search")
    public ApiResponse<List<ReviewResponse.LectureSearchResponse>> searchLecturesForReview(
            @Parameter(description = "강의 키워드") @RequestParam String keyword) {

        List<ReviewResponse.LectureSearchResponse> result = reviewService.searchLecturesForReview(keyword);
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "강의평 검색 API", description = "키워드로 강의평을 검색합니다. reviewId, rate, content, studyTime, likes, imgurl, createdAt을 반환합니다.")
    @GetMapping("/search")
    public ApiResponse<List<ReviewResponse.SearchReviewResponse>> searchReviews(
            @Parameter(description = "리뷰 내용 키워드") @RequestParam String keyword,
            @Parameter(description = "정렬 기준 (rating, createdAt, likes)")
            @RequestParam(defaultValue = "rating") String sortBy,
            @Parameter(description = "정렬 방향 (asc, desc)")
            @RequestParam(defaultValue = "desc") String direction,
            @Parameter(description = "페이지 번호 (0부터 시작)")
            @RequestParam(defaultValue = "0") @Min(0) Integer pageNumber,
            @Parameter(description = "페이지 크기")
            @RequestParam(defaultValue = "5") Integer size) {

        List<ReviewResponse.SearchReviewResponse> result =
                reviewSearchService.searchReviewsByKeyword(keyword, pageNumber, size, sortBy, direction);

        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "강의평 등록 API", description = "강의평을 등록합니다.")
    @PostMapping(value="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> postReview(@RequestPart("request") ReviewRequest.postReviewDTO request,
                                          @RequestPart(value = "image", required = false) MultipartFile file) {
        Long reviewId = reviewService.createReview(request, file);
        return ApiResponse.onSuccess("리뷰 등록이 완료되었습니다. ReivewId = "+reviewId.toString());
    }
}
