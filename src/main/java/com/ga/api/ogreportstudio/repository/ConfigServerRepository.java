package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.ConfigServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigServerRepository extends JpaRepository<ConfigServer, Integer> {
    long countByNameConfigServerEqualsIgnoreCase(String nameConfigServer);
}