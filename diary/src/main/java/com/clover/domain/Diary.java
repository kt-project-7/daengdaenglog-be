package com.clover.domain;

import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "diary")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "emotion_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmotionType emotionType;

    @Column(name = "weather_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WeatherType weatherType;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Diary(Long petId, EmotionType emotionType, WeatherType weatherType, String content) {
        this.petId = petId;
        this.emotionType = emotionType;
        this.weatherType = weatherType;
        this.content = content;
    }
}
