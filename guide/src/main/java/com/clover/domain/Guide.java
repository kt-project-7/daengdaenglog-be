package com.clover.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "guide")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Guide extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "general_info", nullable = false)
    private String generalInfo;

    @Column(name = "routine_info", nullable = false)
    private String routineInfo;

    @Column(name = "feeding_info", nullable = false)
    private String feedingInfo;

    @Column(name = "health_info", nullable = false)
    private String healthInfo;

    @Column(name = "special_info", nullable = false)
    private String specialInfo;

    @Column(name = "emergency_info", nullable = false)
    private String emergencyInfo;

    @Builder
    public Guide(Long petId, String generalInfo, String routineInfo, String feedingInfo, String healthInfo, String specialInfo, String emergencyInfo) {
        this.petId = petId;
        this.generalInfo = generalInfo;
        this.routineInfo = routineInfo;
        this.feedingInfo = feedingInfo;
        this.healthInfo = healthInfo;
        this.specialInfo = specialInfo;
        this.emergencyInfo = emergencyInfo;
    }
}
