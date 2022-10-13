package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.TypeResources;
import com.ga.api.ogreportstudio.repository.TypeResourcesRepository;
import com.ga.api.ogreportstudio.services.TypeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeResourceServiceImple implements TypeResourceService {

    @Autowired
    private TypeResourcesRepository typeResourcesRepository;
    @Override
    public TypeResources save(TypeResources typeResources) {
        return typeResourcesRepository.save(typeResources);
    }

    @Override
    public TypeResources findByNameTypeResourceEqualsIgnoreCase(String nameTypeResource) {
        Optional<TypeResources> oTR = typeResourcesRepository.findByNameTypeResourceEqualsIgnoreCase(nameTypeResource);
        return oTR.isPresent()?oTR.get():null;
    }

    @Override
    public long countByNameTypeResourceEqualsIgnoreCase(String nameTypeResource) {
        return typeResourcesRepository.countByNameTypeResourceEqualsIgnoreCase(nameTypeResource);
    }
}
