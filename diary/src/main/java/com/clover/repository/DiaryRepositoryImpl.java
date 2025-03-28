package com.clover.repository;

import com.clover.dto.response.DiarySimpleResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.clover.domain.QDiary.diary;

@RequiredArgsConstructor
@Repository
public class DiaryRepositoryImpl implements DiaryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<DiarySimpleResponse> getDiaryList(Long petId, int page, int size) {
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
                .offset((long) page * size)
                .limit(size)
                .fetch();
    }
}
