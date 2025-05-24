package com.example.UMC8th_MiniProject.Repository;

import com.example.UMC8th_MiniProject.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    // 강의는 카테고리와 레벨에따라 나뉜다.
    List<Lecture> findByCategoryAndLevel(Category category, Level level);
    // 추가
    List<Lecture> findByCategory(Category category);
    List<Lecture> findByLevel(Level level);
    List<Lecture> findByNameContaining(String keyword);

}
