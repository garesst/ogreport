package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.TypeExecute;
import com.ga.api.ogreportstudio.repository.TypeExecuteRepository;
import com.ga.api.ogreportstudio.services.TypeExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeExecuteServiceImpl implements TypeExecuteService {

    @Autowired
    TypeExecuteRepository typeExecuteRepository;
    @Override
    public TypeExecute save(TypeExecute typeExecute) {
        return typeExecuteRepository.save(typeExecute);
    }

    @Override
    public Optional<TypeExecute> findByNameTypeExecuteEqualsIgnoreCase(String nameTypeExecute) {
        return typeExecuteRepository.findByNameTypeExecuteEqualsIgnoreCase(nameTypeExecute);
    }
}
