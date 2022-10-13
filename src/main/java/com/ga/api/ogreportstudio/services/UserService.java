package com.ga.api.ogreportstudio.services;

import com.ga.api.ogreportstudio.model.Role;
import com.ga.api.ogreportstudio.model.User;

public interface UserService {
    User findUserByEmail(String email);
    User findUserByUserName(String userName);
    User saveUser(User user);
    Role findRolByName(String name);
    Role saveRol(Role rol);
}
