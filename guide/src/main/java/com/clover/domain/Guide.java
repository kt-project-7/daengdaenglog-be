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

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Guide(Long petId, String content) {
        this.petId = petId;
        this.content = content;
    }
}
