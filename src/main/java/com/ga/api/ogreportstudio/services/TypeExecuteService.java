package com.ga.api.ogreportstudio.services;

import com.ga.api.ogreportstudio.model.TypeExecute;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TypeExecuteService {

    TypeExecute save(TypeExecute typeExecute);
    Optional<TypeExecute> findByNameTypeExecuteEqualsIgnoreCase(@NonNull String nameTypeExecute);
}
