package com.clover.repository;

import com.clover.domain.UserInitOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInitOutboxRepository extends JpaRepository<UserInitOutbox, Long> {
}
