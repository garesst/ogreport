package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.Queries;
import com.ga.api.ogreportstudio.repository.QueriesRepository;
import com.ga.api.ogreportstudio.services.QueriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueriesServiceImpl implements QueriesService {
    @Autowired
    QueriesRepository queriesRepository;
    @Override
    public Queries save(Queries queries) {
        return queriesRepository.save(queries);
    }

    @Override
    public Optional<Queries> findByIdEquals(int id) {
        return queriesRepository.findByIdEquals(id);
    }
}
