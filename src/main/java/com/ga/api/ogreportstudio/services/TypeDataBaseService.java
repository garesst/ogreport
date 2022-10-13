package com.ga.api.ogreportstudio.services;

import com.ga.api.ogreportstudio.model.TypeDataBase;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TypeDataBaseService {
    TypeDataBase save(TypeDataBase typeDataBase);
    Optional<TypeDataBase> findByNameTypeDataBaseEqualsIgnoreCase(@NonNull String nameTypeDataBase);
}
