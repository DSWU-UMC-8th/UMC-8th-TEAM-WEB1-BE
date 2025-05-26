package com.example.UMC8th_MiniProject.repository;

import com.example.UMC8th_MiniProject.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByCategoryAndLevel(Category category, Level level);
    List<Lecture> findByCategory(Category category);
    List<Lecture> findByLevel(Level level);
    List<Lecture> findByNameContaining(String keyword);
}
