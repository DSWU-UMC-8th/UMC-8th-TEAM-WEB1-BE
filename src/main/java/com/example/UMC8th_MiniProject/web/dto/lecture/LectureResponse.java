package com.example.UMC8th_MiniProject.web.dto;

import com.example.UMC8th_MiniProject.domain.Lecture;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.domain.enums.Platform;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class LectureResponse {
    private Long lectureId;
    private String name;
    private String teacher;
    private Platform platform;
    private BigDecimal totalRating;
    private Category category;
    private Level level;

    public LectureResponse(Lecture lecture){
        this.lectureId = lecture.getLectureId();
        this.name = lecture.getName();
        this.teacher = lecture.getTeacher();
        this.platform = lecture.getPlatform();
        this.totalRating = lecture.getTotalRating();
        this.category = lecture.getCategory();
        this.level = lecture.getLevel();
    }
}
