package com.ga.api.ogreportstudio.dao.queries_dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParametersDao {
    private String parametersQueriesExecute;
    private String parametersQueriesDescription;
    private String parametersQueriesName;
    private Integer parametersQueriesPosition;
    private String parametersQueriesType;
    private String parametersQueriesValueFormat;
    private String dataSourceId;
    private String queriesId;
    private String typeExecuteId;
}
