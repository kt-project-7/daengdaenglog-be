package com.clover.domain;

import com.clover.domain.type.Gender;
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "neutered", nullable = false)
    private Boolean neutered;

    @Column(name = "image_uri", nullable = false)
    private String imageUri;

    @Column(name = "pbti", nullable = false)
    private String pbti;

    @Column(name = "pet_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetType petType;

    @Builder
    public Pet(Long userId, String name, String breed, Integer age, Double weight, Gender gender, Boolean neutered, String imageUri, String pbti, PetType petType) {
        this.userId = userId;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.neutered = neutered;
        this.imageUri = imageUri;
        this.pbti = pbti;
        this.petType = petType;
    }

    public void updatePbti(String pbti) {
        this.pbti = pbti;
    }
}
