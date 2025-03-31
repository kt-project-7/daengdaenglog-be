package com.clover.repository;

import com.clover.dto.response.DiarySimpleResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepositoryCustom {
    List<DiarySimpleResponse> getDiaryList(Long userId, int page, int size);
    Optional<Long> findTodayDiaryId(Long petId);
}
