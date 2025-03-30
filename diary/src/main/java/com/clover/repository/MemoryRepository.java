package com.clover.repository;

import com.clover.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {

    Optional<Memory> findByDiaryId(Long diaryId);
}
