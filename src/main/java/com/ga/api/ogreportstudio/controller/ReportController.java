package com.ga.api.ogreportstudio.controller;

import com.ga.api.ogreportstudio.dao.dataSource_dao.RequestDataSource;
import com.ga.api.ogreportstudio.dao.report.RequestExport;
import com.ga.api.ogreportstudio.model.*;
import com.ga.api.ogreportstudio.services.impl.*;
import com.ga.api.ogreportstudio.services.managament.ReportManagament;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportsServiceImpl reportsService;
    private final QueriesServiceImpl queriesService;
    private final ParametersQueryServiceImpl parametersQueryService;
    private final NodeResourcesServiceImpl nodeResourcesService;
    private final ResourcesServiceImpl resourcesService;
    private final TypeResourceServiceImple typeResourceServiceImple;

    public ReportController(ReportsServiceImpl reportsService, QueriesServiceImpl queriesService, ParametersQueryServiceImpl parametersQueryService, NodeResourcesServiceImpl nodeResourcesService, ResourcesServiceImpl resourcesService, TypeResourceServiceImple typeResourceServiceImple) {
        this.reportsService = reportsService;
        this.queriesService = queriesService;
        this.parametersQueryService = parametersQueryService;
        this.nodeResourcesService = nodeResourcesService;
        this.resourcesService = resourcesService;
        this.typeResourceServiceImple = typeResourceServiceImple;
    }

    @PostMapping(path = "/exportPDF",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> exportPDF(@RequestBody RequestExport request) throws UnirestException {
        RequestExport rE = new RequestExport();
        if(request.getDataSource()!=null){
            NodeResources nR = nodeResourcesService.findByIdEqualsAndIconEquals(UUID.fromString(request.getNameResource()),
                    typeResourceServiceImple.findByNameTypeResourceEqualsIgnoreCase("report"));
            Reports rES = nR.getReports();
            Resources rSS = nR.getResources();
            ReportManagament reportManagament = new ReportManagament();
            InputStream report = new ByteArrayInputStream(Base64.getDecoder().decode(rSS.getValueResources()));
            InputStream date = new ByteArrayInputStream(Base64.getDecoder().decode(request.getDataSource()));
            ByteArrayResource ba= reportManagament.exportReportToPDF(report,date,rE.getDoParam(),request.getSelect());
            return ResponseEntity.ok(Base64.getEncoder().encodeToString(ba.getByteArray()));
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping(path = "/exportHTML")
    public ResponseEntity<String> exportHTML() throws UnirestException, IOException {
        RequestExport rE = new RequestExport();
        rE.setNameResource("521fecad-25d9-428b-9daa-148d345221f2");
        Map<String,String> m1 = new HashMap<>();
        Map<String,Object> m2 = new HashMap<>();
        m1.put("6804a7fe-b394-4e36-a300-a24e4bc1c2ff","fdc5f7c23a8746dc4a99");
        rE.setDoGet(m1);
        rE.setDoPost(null);
        m2.put("title","Visitas por Mes");
        rE.setDoParam(m2);
        //request end
        NodeResources nR = nodeResourcesService.findByIdEqualsAndIconEquals(UUID.fromString(rE.getNameResource()),
                typeResourceServiceImple.findByNameTypeResourceEqualsIgnoreCase("report"));
        Reports rES = nR.getReports();
        Resources rSS = nR.getResources();
        Queries qES= rES.getReports();
        DataSource dS = qES.getDataSource();
        String valueGEt = "";
        for (Map.Entry<String,String> e: rE.getDoGet().entrySet()
        ) {
            valueGEt+="/"+e.getValue();
        }
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get(dS.getUrlDataSource()+valueGEt)
                .asString();
        System.out.println(response.getBody());
        ReportManagament reportManagament = new ReportManagament();
        InputStream report = new ByteArrayInputStream(Base64.getDecoder().decode(rSS.getValueResources()));
        InputStream date = new ByteArrayInputStream(response.getBody().getBytes(StandardCharsets.UTF_8));
        byte[] ba= reportManagament.exportReportToHTML(report,date,rE.getDoParam(),qES.getBodyExecuteDataSource());
        return ResponseEntity.ok(Base64.getEncoder().encodeToString(reportManagament.generateHTMLFromPDF(ba)));
    }
}
