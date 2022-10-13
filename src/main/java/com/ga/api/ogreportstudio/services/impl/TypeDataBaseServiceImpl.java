package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.TypeDataBase;
import com.ga.api.ogreportstudio.repository.TypeDataBaseRepository;
import com.ga.api.ogreportstudio.services.TypeDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeDataBaseServiceImpl implements TypeDataBaseService {
    @Autowired
    private TypeDataBaseRepository typeDataBaseRepository;

    @Override
    public TypeDataBase save(TypeDataBase typeDataBase) {
        return typeDataBaseRepository.save(typeDataBase);
    }

    @Override
    public Optional<TypeDataBase> findByNameTypeDataBaseEqualsIgnoreCase(String nameTypeDataBase) {
        return typeDataBaseRepository.findByNameTypeDataBaseEqualsIgnoreCase(nameTypeDataBase);
    }
}
