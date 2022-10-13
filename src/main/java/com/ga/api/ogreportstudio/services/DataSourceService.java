package com.ga.api.ogreportstudio.services;

import com.ga.api.ogreportstudio.model.DataSource;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface DataSourceService {
    DataSource save(DataSource dataSource);
    long countByNameDataSourceEqualsIgnoreCase(@NonNull String nameDataSource);
    Optional<DataSource> findByNameDataSourceEqualsIgnoreCase(@NonNull String nameDataSource);
}
