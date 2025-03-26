package com.clover.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "memory")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Memory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "diary_id", nullable = false)
    private Long diaryId;

    @Column(name = "image_uri", nullable = false)
    private String imageUri;

    @Builder
    public Memory(Long diaryId, String imageUri) {
        this.diaryId = diaryId;
        this.imageUri = imageUri;
    }
}
