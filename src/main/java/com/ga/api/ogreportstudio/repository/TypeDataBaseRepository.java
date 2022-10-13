package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.TypeDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeDataBaseRepository extends JpaRepository<TypeDataBase, Integer> {
    Optional<TypeDataBase> findByNameTypeDataBaseEqualsIgnoreCase(@NonNull String nameTypeDataBase);
}