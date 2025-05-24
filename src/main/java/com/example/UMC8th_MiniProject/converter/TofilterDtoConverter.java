package com.example.UMC8th_MiniProject.converter;

import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.domain.enums.StudyTime;
import com.example.UMC8th_MiniProject.web.dto.review.ReviewFilterRequestDTO;

public class TofilterDtoConverter {

    public static ReviewFilterRequestDTO toReviewFilterRequestDTO(Category category,Level level,StudyTime studyTime){
        return ReviewFilterRequestDTO.builder()
                .category(category)
                .level(level)
                .studyTime(studyTime)
                .build();
    }
}
