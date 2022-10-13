package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.DataSource;
import com.ga.api.ogreportstudio.repository.DataSourceRepository;
import com.ga.api.ogreportstudio.services.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataSourceServiceImpl implements DataSourceService {
    @Autowired
    DataSourceRepository dataSourceRepository;
    @Override
    public DataSource save(DataSource dataSource) {
        return dataSourceRepository.save(dataSource);
    }

    @Override
    public long countByNameDataSourceEqualsIgnoreCase(String nameDataSource) {
        return dataSourceRepository.countByNameDataSourceEqualsIgnoreCase(nameDataSource);
    }

    @Override
    public Optional<DataSource> findByNameDataSourceEqualsIgnoreCase(String nameDataSource) {
        return dataSourceRepository.findByNameDataSourceEqualsIgnoreCase(nameDataSource);
    }
}
