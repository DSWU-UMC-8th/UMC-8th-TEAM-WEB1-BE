package com.example.UMC8th_MiniProject.web.controller;

import ch.qos.logback.core.model.Model;
import com.example.UMC8th_MiniProject.service.LectureService.SearchService;
import com.example.UMC8th_MiniProject.web.dto.LectureResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "강의 검색", description = "강의 키워드 검색 API")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<String>> search(@Parameter(description = "검색할 강의 키워드 (예: 스프링, 파이썬 등)", required = true) @RequestParam(value = "keyword") String keyword){
        List<String> result = searchService.findAllByKeyword(keyword);
        return ResponseEntity.ok(result);
    }

}
