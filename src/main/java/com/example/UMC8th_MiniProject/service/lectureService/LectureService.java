package com.example.UMC8th_MiniProject.service.lectureService;

import com.example.UMC8th_MiniProject.domain.Review;
import com.example.UMC8th_MiniProject.repository.LectureRepository;
import com.example.UMC8th_MiniProject.domain.Lecture;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.repository.ReviewRepository;
import com.example.UMC8th_MiniProject.web.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {
;
    private final LectureRepository lectureRepository;
    private final ReviewRepository reviewRepository;

    // 필터링 없이 전체 강의 조회
    public List<LectureResponse> getAllLectures(){
        return lectureRepository.findAll().stream().map(LectureResponse::new).collect(Collectors.toList());
    }
    //카테고리와 레벨을 기준으로 강의 필터링
    public List<LectureResponse> getLectureByCategoryAndLevel(Category category, Level level) {
        return lectureRepository.findByCategoryAndLevel(category, level)
                .stream().map(LectureResponse::new).toList();
    }

    //추가
    public List<LectureResponse> getLecturesByCategory(Category category) {
        return lectureRepository.findByCategory(category)
                .stream().map(LectureResponse::new).toList();
    }
    public List<LectureResponse> getLecturesByLevel(Level level) {
        return lectureRepository.findByLevel(level)
                .stream().map(LectureResponse::new).toList();
    }
    public LectureResponse getLectureById(Long lectureId){
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new IllegalArgumentException("해당 강의를 찾을 수 없습니다."));
        return new LectureResponse(lecture);
    }

    public List<BigDecimal> getRatingList (Lecture lecture){
        List<Review> lectureReviewList = reviewRepository.findAllByLecture(lecture);
        List<BigDecimal> lectureReviewRatingList = lectureReviewList.stream()
                .map(Review::getRating)
                .collect(Collectors.toList());

        return lectureReviewRatingList;
    }

    public int[] getRatingCounts(List<BigDecimal>  lectureReviewRatingList){

        int[] ratingCount = new int[5];
        lectureReviewRatingList.stream()
                .forEach(i -> {
                    if (i.compareTo(new BigDecimal("2")) < 0) {
                        ratingCount[0]++;
                    } else if (i.compareTo(new BigDecimal("3")) < 0) {
                        ratingCount[1]++;
                    } else if (i.compareTo(new BigDecimal("4")) < 0) {
                        ratingCount[2]++;
                    } else if (i.compareTo(new BigDecimal("5")) < 0) {
                        ratingCount[3]++;
                    } else {
                        ratingCount[4]++;
                    }
                });
        return ratingCount;
    }

}
