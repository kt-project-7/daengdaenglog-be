package com.clover.repository;

import com.clover.dto.response.DiarySimpleResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepositoryCustom {
    List<DiarySimpleResponse> getDiaryList(Long userId, int page, int size);
}
