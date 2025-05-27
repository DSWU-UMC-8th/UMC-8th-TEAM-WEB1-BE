package com.example.UMC8th_MiniProject.service.lectureService;

import com.example.UMC8th_MiniProject.repository.LectureRepository;
import com.example.UMC8th_MiniProject.domain.Lecture;
import com.example.UMC8th_MiniProject.web.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final LectureRepository lectureRepository;

    public List<LectureResponse> findAllByKeyword(final String keyword){
        return lectureRepository.findByNameContaining(keyword).stream()
                .map(LectureResponse::new)
                .collect(Collectors.toList());
    }
}