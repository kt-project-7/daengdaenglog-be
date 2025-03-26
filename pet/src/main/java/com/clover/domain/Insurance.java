package com.clover.domain;

import com.clover.domain.type.InsuranceType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "insurance")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Insurance extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "insurance_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InsuranceType insuranceType;

    @Builder
    public Insurance(Long petId, InsuranceType insuranceType) {
        this.petId = petId;
        this.insuranceType = insuranceType;
    }
}
