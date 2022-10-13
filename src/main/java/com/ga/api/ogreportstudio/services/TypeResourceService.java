package com.ga.api.ogreportstudio.services;

import com.ga.api.ogreportstudio.model.TypeResources;

import java.util.Optional;

public interface TypeResourceService {
    TypeResources save(TypeResources typeResources);
    TypeResources findByNameTypeResourceEqualsIgnoreCase(String nameTypeResource);
    long countByNameTypeResourceEqualsIgnoreCase(String nameTypeResource);
}
