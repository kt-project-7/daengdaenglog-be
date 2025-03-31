package com.clover.domain;

import com.clover.domain.type.ScheduleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Table(name = "schedule_time")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ScheduleTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "diary_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @Column(name = "schedule_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Builder
    public ScheduleTime(Diary diary, ScheduleType scheduleType, LocalTime startTime, LocalTime endTime) {
        this.diary = diary;
        this.scheduleType = scheduleType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void updateDiary(Diary diary) {
        this.diary = diary;
    }
}
