package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.ParametersQueries;
import com.ga.api.ogreportstudio.repository.ParametersQueriesRepository;
import com.ga.api.ogreportstudio.services.ParametersQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ParametersQueryServiceImpl implements ParametersQueryService {
    @Autowired
    ParametersQueriesRepository queriesRepository;
    @Override
    public ParametersQueries save(ParametersQueries parametersQueries) {
        return queriesRepository.save(parametersQueries);
    }
}
