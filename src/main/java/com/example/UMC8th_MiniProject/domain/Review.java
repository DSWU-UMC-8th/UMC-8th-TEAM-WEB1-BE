package com.example.UMC8th_MiniProject.domain;

import com.example.UMC8th_MiniProject.domain.common.BaseEntity;
import com.example.UMC8th_MiniProject.domain.enums.StudyTime;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Review")
@AttributeOverride(name = "createdAt", column = @Column(name = "created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at"))
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lectureId", nullable = false)
    private Lecture lecture;

    @Column(name = "rating", nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "studyTime", nullable = false)
    private StudyTime studyTime;

    @Column(name = "likes")
    private Integer likes = 0;

    private String imgUrl;

    public void increaseLikes() {
        this.likes = this.likes + 1;
    }
}