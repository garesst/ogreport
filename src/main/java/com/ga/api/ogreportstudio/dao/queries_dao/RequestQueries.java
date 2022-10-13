package com.ga.api.ogreportstudio.dao.queries_dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestQueries {
    private String queriesBodyExecute;
    private Boolean queriesBuilt;
    private String queriesCharactersRemove;
    private String queriesHeadExecute;
    private Boolean queriesMultiPart;
    private String queriesName;
    private String queriesSeparatorCharacters;
    private String dataSourceId;
    private String typeExecuteIdBody;
    private String typeExecuteIdHead;
    private String typeQueriesId;
    private List<ParametersDao> parametrosQuery;
}
