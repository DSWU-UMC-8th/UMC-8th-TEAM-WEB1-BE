package com.example.UMC8th_MiniProject.service.LectureService;

import com.example.UMC8th_MiniProject.Repository.LectureRepository;
import com.example.UMC8th_MiniProject.domain.Lecture;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.web.dto.LectureResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {
;
    private final LectureRepository lectureRepository;

    // 수동 초기화
    public LectureService(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }
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

}
