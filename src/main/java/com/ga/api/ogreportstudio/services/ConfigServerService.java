package com.ga.api.ogreportstudio.services;

import com.ga.api.ogreportstudio.model.ConfigServer;

public interface ConfigServerService {
    long countByNameConfigServerEqualsIgnoreCase(String nameConfigServer);
    ConfigServer save(ConfigServer s);
}
