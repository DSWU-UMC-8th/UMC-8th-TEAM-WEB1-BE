package com.example.UMC8th_MiniProject.domain;

import com.example.UMC8th_MiniProject.domain.common.BaseEntity;
import com.example.UMC8th_MiniProject.domain.enums.Category;
import com.example.UMC8th_MiniProject.domain.enums.Level;
import com.example.UMC8th_MiniProject.domain.enums.Platform;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Lecture")
@AttributeOverride(name = "createdAt", column = @Column(name = "createdDate"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lectureId")
    private Long lectureId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "teacher", nullable = false, length = 10)
    private String teacher;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private Platform platform;

    @Column(name = "totalRating", precision = 3, scale = 2)
    private BigDecimal totalRating;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private Level level;

}