package com.clover.repository;

import com.clover.domain.GuideInitOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideInitOutboxRepository extends JpaRepository<GuideInitOutbox, Long> {
}
