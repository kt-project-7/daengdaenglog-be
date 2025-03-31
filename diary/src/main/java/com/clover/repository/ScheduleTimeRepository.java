package com.clover.repository;

import com.clover.domain.ScheduleTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTimeRepository extends JpaRepository<ScheduleTime, Long> {
}
