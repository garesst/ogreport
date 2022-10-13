package com.ga.api.ogreportstudio.dao.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestReport {
    private String query;
    private String report;
    private String node;
    private String nameReport;
    private String descReport;
}
