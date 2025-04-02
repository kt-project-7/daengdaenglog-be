package com.clover.repository;

import com.clover.domain.GuideInitOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuideInitOutboxRepository extends JpaRepository<GuideInitOutbox, Long> {

    Optional<GuideInitOutbox> findByGuideId(Long guideId);
}
