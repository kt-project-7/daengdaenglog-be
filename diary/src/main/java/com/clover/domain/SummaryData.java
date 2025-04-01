package com.clover.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "summary_data")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SummaryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "year", nullable = false)
    private Long year;

    @Column(name = "month", nullable = false)
    private Long month;

    @Setter
    @Column(name = "description", length = 1024)
    private String description;

    @Builder
    public SummaryData(Long petId, Long year, Long month, String description) {
        this.petId = petId;
        this.year = year;
        this.month = month;
        this.description = description;
    }
}
