package com.clover.repository;

import com.clover.dto.response.DiarySimpleResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.clover.domain.QDiary.diary;

@RequiredArgsConstructor
@Repository
public class DiaryRepositoryImpl implements DiaryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<DiarySimpleResponse> getDiaryList(Long petId) {
        return jpaQueryFactory
                .select(Projections.constructor(DiarySimpleResponse.class,
                        diary.id,
                        diary.title,
                        diary.content,
                        diary.createdDate
                ))
                .from(diary)
                .where(diary.petId.eq(petId))
                .orderBy(diary.createdDate.desc())
                .fetch();
    }

    @Override
    public Optional<Long> findTodayDiaryId(Long petId) {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = todayStart.plusDays(1);

        return Optional.ofNullable(
                jpaQueryFactory
                        .select(diary.id)
                        .from(diary)
                        .where(diary.petId.eq(petId), diary.createdDate.between(todayStart, todayEnd))
                        .orderBy(diary.id.asc())
                        .fetchFirst());
    }
}
