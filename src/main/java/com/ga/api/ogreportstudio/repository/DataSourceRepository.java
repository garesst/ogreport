package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.DataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Integer> {
    long countByNameDataSourceEqualsIgnoreCase(@NonNull String nameDataSource);

    Optional<DataSource> findByNameDataSourceEqualsIgnoreCase(@NonNull String nameDataSource);
}