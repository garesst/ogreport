package com.ga.api.ogreportstudio.repository;

import com.ga.api.ogreportstudio.model.NodeResources;
import com.ga.api.ogreportstudio.model.TypeResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NodeResourcesRepository extends JpaRepository<NodeResources, UUID> {
    Optional<NodeResources> findByIdEqualsAndIconEquals(@NonNull UUID id, @NonNull TypeResources icon);

    long countByDescriptionNodeResourcesEqualsIgnoreCaseAndChildrenNodeResourcesEquals(@NonNull String descriptionNodeResources, NodeResources childrenNodeResources);


}