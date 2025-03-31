package com.clover.domain;

import com.clover.domain.type.ProgressType;
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

    @Column(name = "hospital_name", nullable = false)
    private String hospitalName;

    @Column(name = "medical_fee", nullable = false)
    private Long medicalFee;

    @Column(name = "claim_amount")
    private Long claimAmount;

    @Column(name = "refund_amount")
    private Long refundAmount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "progress_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgressType ProgressType;

    @Builder
    public InsuranceClaim(Insurance insurance, String hospitalName, Long medicalFee, Long claimAmount, Long refundAmount, String description, ProgressType progressType) {
        this.insurance = insurance;
        this.hospitalName = hospitalName;
        this.medicalFee = medicalFee;
        this.claimAmount = claimAmount;
        this.refundAmount = refundAmount;
        this.description = description;
        ProgressType = progressType;
    }
}
