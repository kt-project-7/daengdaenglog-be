package com.clover.repository;

import com.clover.domain.Diary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long>, DiaryRepositoryCustom {

    @EntityGraph(attributePaths = {"scheduleTimeList"})
    @Query("select d from Diary d where d.id = :id")
    Optional<Diary> findByIdFetch(Long id);

    List<Diary> findAllByPetId(Long petId);
}
