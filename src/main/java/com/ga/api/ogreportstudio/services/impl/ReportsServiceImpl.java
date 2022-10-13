package com.ga.api.ogreportstudio.services.impl;

import com.ga.api.ogreportstudio.model.Reports;
import com.ga.api.ogreportstudio.repository.ReportsRepository;
import com.ga.api.ogreportstudio.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportsServiceImpl implements ReportsService {
    @Autowired
    ReportsRepository reportsRepository;
    @Override
    public Reports save(Reports reports) {
        return reportsRepository.save(reports);
    }
}
