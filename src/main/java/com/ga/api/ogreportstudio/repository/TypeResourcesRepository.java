package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.TypeResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeResourcesRepository extends JpaRepository<TypeResources, String> {
    Optional<TypeResources> findByNameTypeResourceEqualsIgnoreCase(String nameTypeResource);

    long countByNameTypeResourceEqualsIgnoreCase(String nameTypeResource);
}