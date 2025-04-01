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

    @Column(name = "image_uri")
    private String imageUri;

    @Column(name = "pbti")
    private String pbti;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pet_type", nullable = false)
    private String petType;

    @Column(name = "status", nullable = false)
    private Status status;

    @Builder
    public PetInitOutbox(Long userId, String imageUri, String pbti, String name, String petType, Status status) {
        this.userId = userId;
        this.imageUri = imageUri;
        this.pbti = pbti;
        this.name = name;
        this.petType = petType;
        this.status = status;
    }
}
