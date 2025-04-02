package com.clover.domain;

import com.clover.domain.type.GuideType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@SQLDelete(sql = "UPDATE guide_init_outbox SET is_deleted = true WHERE id = ?")
@Table(name = "guide_init_outbox")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class GuideInitOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long guideId;

    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "guide_type", nullable = false)
    @Enumerated(EnumType.STRING)
    GuideType guideType;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public GuideInitOutbox(Long guideId, Long petId, GuideType guideType) {
        this.guideId = guideId;
        this.petId = petId;
        this.guideType = guideType;
    }
}
