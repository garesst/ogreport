package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueriesRepository extends JpaRepository<Queries, Integer> {
    Optional<Queries> findByIdEquals(@NonNull int id);
}