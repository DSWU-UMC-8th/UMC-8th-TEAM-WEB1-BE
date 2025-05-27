package com.example.UMC8th_MiniProject.web.controller;

import com.example.UMC8th_MiniProject.service.lectureService.SearchService;
import com.example.UMC8th_MiniProject.web.dto.LectureResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lectures")
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "강의 검색 API", description = "강의 제목 기반으로 강의를 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<LectureResponse>> search(@Parameter(description = "검색할 강의 키워드 (예: 스프링, 파이썬 등)", required = true) @RequestParam(value = "keyword") String keyword){
        List<LectureResponse> result = searchService.findAllByKeyword(keyword);
        return ResponseEntity.ok(result);
    }
}
