package com.clover.repository;

import com.clover.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByUserId(Long userId);
    Boolean existsByIdAndUserId(Long petId, Long userId);
}
