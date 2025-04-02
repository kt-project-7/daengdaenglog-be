package com.clover.repository;

import com.clover.domain.SummaryData;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryDataRepository extends JpaRepository<SummaryData, Long> {

    Optional<SummaryData> findByPetIdAndYearAndMonth(Long petId, Long year, Long month);

    List<SummaryData> findAllByPetId(Long petId);
}
