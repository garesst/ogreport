package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.ConfigServer;
import com.ga.api.ogreportstudio.repository.ConfigServerRepository;
import com.ga.api.ogreportstudio.services.ConfigServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServerServiceImpl implements ConfigServerService {

    @Autowired
    private ConfigServerRepository configServerRepository;
    @Override
    public long countByNameConfigServerEqualsIgnoreCase(String nameConfigServer) {
        return configServerRepository.countByNameConfigServerEqualsIgnoreCase(nameConfigServer);
    }

    @Override
    public ConfigServer save(ConfigServer s) {
        return configServerRepository.save(s);
    }
}
