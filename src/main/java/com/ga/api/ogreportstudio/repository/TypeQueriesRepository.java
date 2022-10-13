package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.TypeQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeQueriesRepository extends JpaRepository<TypeQueries, Integer> {
}