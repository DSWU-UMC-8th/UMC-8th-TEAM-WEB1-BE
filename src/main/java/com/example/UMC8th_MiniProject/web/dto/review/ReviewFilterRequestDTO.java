package com.example.UMC8th_MiniProject.web.dto.review;

import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.domain.enums.StudyTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ReviewFilterRequestDTO {
    Category category;
    Level level;
    StudyTime studyTime;
}
