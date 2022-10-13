package com.ga.api.ogreportstudio.services;

import com.ga.api.ogreportstudio.model.NodeResources;
import com.ga.api.ogreportstudio.model.TypeResources;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface NodeResourcesService {
    NodeResources save(NodeResources nodeResources);
    NodeResources findByIdEqualsAndIconEquals( UUID id,  TypeResources icon);
    long countByDescriptionNodeResourcesEqualsIgnoreCaseAndChildrenNodeResourcesEquals(@NonNull String descriptionNodeResources, NodeResources childrenNodeResources);
}
