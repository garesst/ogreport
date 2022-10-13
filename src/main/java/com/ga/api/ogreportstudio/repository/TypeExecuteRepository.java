package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.TypeExecute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeExecuteRepository extends JpaRepository<TypeExecute, Integer> {
    Optional<TypeExecute> findByNameTypeExecuteEqualsIgnoreCase(@NonNull String nameTypeExecute);
}