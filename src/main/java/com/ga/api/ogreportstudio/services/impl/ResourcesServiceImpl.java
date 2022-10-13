package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.Resources;
import com.ga.api.ogreportstudio.repository.ResourcesRepository;
import com.ga.api.ogreportstudio.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    ResourcesRepository resourcesRepository;
    @Override
    public Resources save(Resources resources) {
        return resourcesRepository.save(resources);
    }
}
