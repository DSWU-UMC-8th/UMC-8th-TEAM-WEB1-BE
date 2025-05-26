package com.example.UMC8th_MiniProject.service.reviewService;

import com.example.UMC8th_MiniProject.domain.Review;
import com.example.UMC8th_MiniProject.repository.ReviewRepository;
import com.example.UMC8th_MiniProject.repository.ReviewSpecification;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewSearchService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponse.SearchReviewResponse> searchReviewsByKeyword(
            String keyword, Integer pageNumber, Integer size, String sortBy, String direction) {

        Specification<Review> spec = Specification.where(ReviewSpecification.contentContains(keyword));

        Sort.Direction sortDirection = "asc".equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortDirection, sortBy));

        Page<Review> reviews = reviewRepository.findAll(spec, pageable);

        return reviews.stream()
                .map(this::toSearchDTO)
                .collect(Collectors.toList());
    }

    private ReviewResponse.SearchReviewResponse toSearchDTO(Review review) {
        return ReviewResponse.SearchReviewResponse.builder()
                .reviewId(review.getReviewId())
                .rate(review.getRating())
                .studyTime(review.getStudyTime())
                .likes(review.getLikes())
                .content(review.getContent())
                .imageUrl(review.getImgUrl())
                .createdAt(review.getCreatedAt())
                .build();
    }
}