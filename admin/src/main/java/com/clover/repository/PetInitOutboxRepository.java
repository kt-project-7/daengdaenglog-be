package com.clover.repository;

import com.clover.domain.PetInitOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetInitOutboxRepository extends JpaRepository<PetInitOutbox, Long> {

    void deleteByEventId(String eventId);
}
