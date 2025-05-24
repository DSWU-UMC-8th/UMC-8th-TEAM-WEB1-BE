package com.example.UMC8th_MiniProject.repository;

import com.example.UMC8th_MiniProject.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    // 강의는 카테고리와 레벨에따라 나뉜다.
    List<Lecture> findByCategoryAndLevel(Category category, Level level);
    // 추가
    List<Lecture> findByCategory(Category category);
    List<Lecture> findByLevel(Level level);

}
