package com.clover.domain;

import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleTime> scheduleTimeList = new ArrayList<>();

    @Builder
    public Diary(Long petId, EmotionType emotionType, WeatherType weatherType, String title, String content, List<ScheduleTime> scheduleTimeList) {
        this.petId = petId;
        this.emotionType = emotionType;
        this.weatherType = weatherType;
        this.title = title;
        this.content = content;
        this.scheduleTimeList = scheduleTimeList;

        if (scheduleTimeList != null) {
            scheduleTimeList.forEach(scheduleTime -> scheduleTime.updateDiary(this));
        } else {
            this.scheduleTimeList = new ArrayList<>();
        }
    }
}
