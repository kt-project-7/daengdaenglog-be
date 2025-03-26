package com.clover.domain;

import com.clover.domain.type.PetType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pet")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Pet extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "image_uri", nullable = false)
    private String imageUri;

    @Column(name = "pbti", nullable = false)
    private String pbti;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pet_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetType petType;

    @Builder
    public Pet(Long userId, String imageUri, String pbti, String name, PetType petType) {
        this.userId = userId;
        this.imageUri = imageUri;
        this.pbti = pbti;
        this.name = name;
        this.petType = petType;
    }
}
