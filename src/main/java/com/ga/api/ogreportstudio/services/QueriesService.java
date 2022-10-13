package com.ga.api.ogreportstudio.services;

import com.ga.api.ogreportstudio.model.Queries;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface QueriesService {
    Queries save(Queries queries);
    Optional<Queries> findByIdEquals(@NonNull int id);
}
