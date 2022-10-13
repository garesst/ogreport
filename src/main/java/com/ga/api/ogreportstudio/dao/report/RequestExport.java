package com.ga.api.ogreportstudio.dao.report;

import com.ga.api.ogreportstudio.dao.dataSource_dao.RequestDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestExport {

    private String nameResource;
    private String typeExport;
    private Map<String,String> doGet;
    private Map<String,String> doPost;
    private Map<String,Object> doParam;
    private String dataSource;
    private String select;

}
