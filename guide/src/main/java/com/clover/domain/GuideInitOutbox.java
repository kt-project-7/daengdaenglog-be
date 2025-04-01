package com.clover.domain;

import com.clover.domain.type.Status;
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

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public GuideInitOutbox(Long guideId, Status status) {
        this.guideId = guideId;
        this.status = status;
    }
}
