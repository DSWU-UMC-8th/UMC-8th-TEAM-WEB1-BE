package com.example.UMC8th_MiniProject.service.reviewService;

import com.example.UMC8th_MiniProject.repository.ReviewRepository;
import com.example.UMC8th_MiniProject.domain.Review;
import com.example.UMC8th_MiniProject.repository.ReviewSpecification;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewFilterRequestDTO;
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
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponse.SearchReviewResponse> reviewFilter(ReviewFilterRequestDTO filterRequestDTO, Integer pageNumber, Integer type){

        Specification<Review> spec = Specification.where(null);
        if (filterRequestDTO.getCategory() != null) {
            spec = spec.and(ReviewSpecification.hasCategory(filterRequestDTO.getCategory()));
        }
        if (filterRequestDTO.getLevel() != null) {
            spec = spec.and(ReviewSpecification.hasLevel(filterRequestDTO.getLevel()));
        }
        if (filterRequestDTO.getStudyTime() != null) {
            spec = spec.and(ReviewSpecification.hasStudyTime(filterRequestDTO.getStudyTime()));
        }

        Pageable pageable;
        if(type == 1){
            pageable = PageRequest.of(pageNumber, 5, Sort.by(Sort.Direction.DESC, "createdAt"));
        }else{
            pageable = PageRequest.of(pageNumber, 5, Sort.by(Sort.Direction.DESC, "likes"));
        }
        Page<Review> reviews = reviewRepository.findAll(spec, pageable);
        return reviews.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ReviewResponse.SearchReviewResponse toDTO(Review review) {
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
