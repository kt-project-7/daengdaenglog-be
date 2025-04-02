package com.clover.domain;

import com.clover.domain.type.GuideType;
import com.clover.domain.type.Status;
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

    @Column(name = "content", length = 1024)
    private String content;

    @Column(name = "guide_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GuideType guideType;

    @Column(name = "statud", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Guide(Long petId, String content, GuideType guideType, Status status) {
        this.petId = petId;
        this.content = content;
        this.guideType = guideType;
        this.status = status;
    }

    public void updateGuide(String content) {
        this.content = content;
        this.status = Status.DONE;
    }

    public void rejectGuide() {
        this.status = Status.REJECTED;
    }
}
