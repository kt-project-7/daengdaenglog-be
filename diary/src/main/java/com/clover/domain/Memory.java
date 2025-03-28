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

    @JoinColumn(name = "diary_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @Column(name = "image_uri", nullable = false)
    private String imageUri;

    @Builder
    public Memory(Diary diary, String imageUri) {
        this.diary = diary;
        this.imageUri = imageUri;
    }
}
