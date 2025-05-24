package com.example.UMC8th_MiniProject.service.LectureService;

import com.example.UMC8th_MiniProject.Repository.LectureRepository;
import com.example.UMC8th_MiniProject.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final LectureRepository lectureRepository;

    public List<String> findAllByKeyword(final String keyword){
        List<Lecture> lectures = lectureRepository.findByNameContaining(keyword);
        System.out.println("검색 키워드: " + keyword);
        lectures.forEach(l -> System.out.println(" - " + l.getName()));
        return lectures.stream().map(Lecture::getName).collect(Collectors.toList());
    }
}