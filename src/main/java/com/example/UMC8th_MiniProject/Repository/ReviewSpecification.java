package com.example.UMC8th_MiniProject.Repository;

import com.example.UMC8th_MiniProject.domain.Review;
import com.example.UMC8th_MiniProject.domain.enums.StudyTime;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {

    public static Specification<Review> hasCategory(Category category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lecture").get("category"), category);
    }

    public static Specification<Review> hasLevel(Level level) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lecture").get("level"), level);
    }

    public static Specification<Review> hasStudyTime(StudyTime studyTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("studyTime"), studyTime);
    }
}