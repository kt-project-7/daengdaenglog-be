package com.clover.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "insurance_claim")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class InsuranceClaim extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "insurance_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Insurance insurance;

    @Column(name = "claim_amount", nullable = false)
    private Long claimAmount;

    @Column(name = "cause", nullable = false)
    private String cause;

    @Builder
    public InsuranceClaim(Insurance insurance, Long claimAmount, String cause) {
        this.insurance = insurance;
        this.claimAmount = claimAmount;
        this.cause = cause;
    }
}
