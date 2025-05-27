package com.example.UMC8th_MiniProject.service.reviewService;

import com.example.UMC8th_MiniProject.apiPayload.code.status.ErrorStatus;
import com.example.UMC8th_MiniProject.apiPayload.exception.handler.TempHandler;
import com.example.UMC8th_MiniProject.aws.s3.AmazonS3Manager;
import com.example.UMC8th_MiniProject.domain.Lecture;
import com.example.UMC8th_MiniProject.domain.Review;
import com.example.UMC8th_MiniProject.repository.LectureRepository;
import com.example.UMC8th_MiniProject.repository.ReviewRepository;
import com.example.UMC8th_MiniProject.repository.ReviewSpecification;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewFilterRequestDTO;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewRequest;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final LectureRepository lectureRepository;
    private final AmazonS3Manager amazonS3Manager;

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
        Lecture lecture = review.getLecture();
        return ReviewResponse.SearchReviewResponse.builder()
                .reviewId(review.getReviewId())
                .rate(review.getRating())
                .studyTime(review.getStudyTime())
                .teacher(lecture.getTeacher())
                .likes(review.getLikes())
                .content(review.getContent())
                .imageUrl(review.getImgUrl())
                .createdAt(review.getCreatedAt())
                .build();
    }

    @Transactional
    public ReviewResponse.LikeResponse increaseLikes(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));

        review.increaseLikes();

        return ReviewResponse.LikeResponse.builder()
                .reviewId(reviewId)
                .currentLikes(review.getLikes())
                .build();
    }

    public List<ReviewResponse.LectureSearchResponse> searchLecturesForReview(String keyword) {
        List<Lecture> lectures = lectureRepository.findByNameContaining(keyword);

        return lectures.stream()
                .map(this::toLectureSearchDTO)
                .collect(Collectors.toList());
    }

    private ReviewResponse.LectureSearchResponse toLectureSearchDTO(Lecture lecture) {
        return ReviewResponse.LectureSearchResponse.builder()
                .lectureId(lecture.getLectureId())
                .name(lecture.getName())
                .teacher(lecture.getTeacher())
                .platform(lecture.getPlatform())
                .build();
    }

    @Transactional
    public Long createReview(ReviewRequest.postReviewDTO request, MultipartFile file){
        Lecture findLecture = lectureRepository.findById(request.getLectureId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.LECTURE_NOT_FOUND));

        String imgUrl = uploadImageIfExists(file);
        Review newReview = Review.builder()
                .lecture(findLecture)
                .rating(request.getRating())
                .content(request.getContent())
                .studyTime(request.getStudyTime())
                .imgUrl(imgUrl)
                .likes(0)
                .build();
        reviewRepository.save(newReview);

        return newReview.getReviewId();
    }

    private String uploadImageIfExists(MultipartFile img) {
        if (img == null || img.isEmpty()) {
            return null;
        }
        final String uuid = UUID.randomUUID().toString();
        final String keyName = amazonS3Manager.generateReviewKeyName(uuid);
        return amazonS3Manager.uploadFile(keyName, img);
    }
}
