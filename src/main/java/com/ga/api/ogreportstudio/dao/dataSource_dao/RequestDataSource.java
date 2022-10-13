package com.ga.api.ogreportstudio.dao.dataSource_dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDataSource {
    private String nameDataSource;
    private String urlDataSource;
    private String usernameDataSource;
    private String pwsDataSource;
    private String typeDataBase;
}
