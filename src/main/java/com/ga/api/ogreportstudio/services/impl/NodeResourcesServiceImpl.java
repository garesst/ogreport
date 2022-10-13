package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.NodeResources;
import com.ga.api.ogreportstudio.model.TypeResources;
import com.ga.api.ogreportstudio.repository.NodeResourcesRepository;
import com.ga.api.ogreportstudio.services.NodeResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NodeResourcesServiceImpl implements NodeResourcesService {

    @Autowired
    private NodeResourcesRepository nodeResourcesRepository;
    @Override
    public NodeResources save(NodeResources nodeResources) {
        return nodeResourcesRepository.save(nodeResources);
    }

    @Override
    public NodeResources findByIdEqualsAndIconEquals(UUID id, TypeResources icon) {
        Optional<NodeResources> nodeResources = nodeResourcesRepository.findByIdEqualsAndIconEquals(id,icon);
        return nodeResources.isPresent()?nodeResources.get():null;
    }

    @Override
    public long countByDescriptionNodeResourcesEqualsIgnoreCaseAndChildrenNodeResourcesEquals(String descriptionNodeResources, NodeResources childrenNodeResources) {
        return nodeResourcesRepository.countByDescriptionNodeResourcesEqualsIgnoreCaseAndChildrenNodeResourcesEquals(descriptionNodeResources,childrenNodeResources);
    }
}
