package com.example.UMC8th_MiniProject.web.controller;

import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.service.LectureService.LectureService;
import com.example.UMC8th_MiniProject.web.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    @GetMapping
    public ResponseEntity<List<LectureResponse>> getLectures(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Level level
    ) {
        List<LectureResponse> result;

        if (category != null && level != null) {
            result = lectureService.getLectureByCategoryAndLevel(category, level);
        } else if (category != null) {
            result = lectureService.getLecturesByCategory(category);
        } else if (level != null) {
            result = lectureService.getLecturesByLevel(level);
        } else {
            result = lectureService.getAllLectures();
        }

        return ResponseEntity.ok(result);
    }
}
