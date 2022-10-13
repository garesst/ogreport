package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, UUID> {
}