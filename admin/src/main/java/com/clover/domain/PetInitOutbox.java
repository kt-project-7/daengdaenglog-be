package com.clover.domain;

import com.clover.domain.type.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_init_outbox")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PetInitOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pet_type", nullable = false)
    private String petType;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Builder
    public PetInitOutbox(Long userId, String name, String petType, String eventId, Status status, boolean isDeleted) {
        this.userId = userId;
        this.name = name;
        this.petType = petType;
        this.eventId = eventId;
        this.status = status;
        this.isDeleted = isDeleted;
    }
}
