package com.ga.api.ogreportstudio.controller;

import com.ga.api.ogreportstudio.dao.Response;
import com.ga.api.ogreportstudio.dao.dataSource_dao.RequestDataSource;
import com.ga.api.ogreportstudio.dao.node_dao.RequestNode;
import com.ga.api.ogreportstudio.dao.queries_dao.ParametersDao;
import com.ga.api.ogreportstudio.dao.queries_dao.RequestQueries;
import com.ga.api.ogreportstudio.dao.report.RequestExport;
import com.ga.api.ogreportstudio.dao.report.RequestReport;
import com.ga.api.ogreportstudio.model.*;
import com.ga.api.ogreportstudio.services.impl.*;
import com.ga.api.ogreportstudio.util.Constans;
import com.ga.api.ogreportstudio.util.Simmetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/configure")
public class ConfigController {

    final Constans constans = new Constans();
    private final ConfigServerServiceImpl configServerService;
    private final UserServiceImpl userService;
    private final TypeResourceServiceImple typeResourceServiceImple;
    private final NodeResourcesServiceImpl nodeResourcesService;
    private final TypeDataBaseServiceImpl typeDataBaseService;
    private final TypeExecuteServiceImpl typeExecuteService;
    private final DataSourceServiceImpl dataSourceService;
    private final QueriesServiceImpl queriesService;
    private final ParametersQueryServiceImpl parametersQueryService;
    private final ResourcesServiceImpl resourcesService;
    private final ReportsServiceImpl reportsService;

    @Autowired
    public ConfigController(ConfigServerServiceImpl configServerService, UserServiceImpl userService,
                            TypeResourceServiceImple typeResourceServiceImple, NodeResourcesServiceImpl nodeResourcesService
            , TypeDataBaseServiceImpl typeDataBaseService, TypeExecuteServiceImpl typeExecuteService, DataSourceServiceImpl dataSourceService, QueriesServiceImpl queriesService, ParametersQueryServiceImpl parametersQueryService, ResourcesServiceImpl resourcesService, ReportsServiceImpl reportsService) {
        this.configServerService = configServerService;
        this.userService = userService;
        this.typeResourceServiceImple = typeResourceServiceImple;
        this.nodeResourcesService = nodeResourcesService;
        this.typeDataBaseService = typeDataBaseService;
        this.typeExecuteService = typeExecuteService;
        this.dataSourceService = dataSourceService;
        this.queriesService = queriesService;
        this.parametersQueryService = parametersQueryService;
        this.resourcesService = resourcesService;
        this.reportsService = reportsService;
    }

    @PostMapping(path = "/configInit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> initializationConfig() {
        Response r = new Response();
        if (configServerService.countByNameConfigServerEqualsIgnoreCase("init") == 0) {
            try {
                createTypeResource(constans.getT_INIT_TYPE_RESOURCE(), constans.getT_DEFAULT_INIT());
                createResource(constans.getT_INIT_RESOURSE(), constans.getT_DEFAULT_INIT());
                createTypeDataBase(constans.getT_TYPE_DATA_BASE(), constans.getT_DEFAULT_INIT(),
                        constans.getT_TYPE_DATA_BASE_DEFAULT(), constans.getT_TYPE_DATA_BASE_DEFAULT_VALUE());
                createTypeExecute(constans.getT_INIT_TYPE_EXECUTE(), constans.getT_TYPE_DATA_BASE_DEFAULT_VALUE());
                createRoles(constans.getT_INIT_USER_ROLES(), constans.getT_DEFAULT_INIT());
                createInitUser(constans.getT_INIT_USER_ADMIN(), constans.getT_DEFAULT_INIT(),
                        constans.getT_INIT_USER_ADMIN_DEFAULT_EMAIL(), constans.getT_INIT_USER_ADMIN_DEFAULT_LASTNAME(),
                        constans.getT_INIT_USER_ADMIN_DEFAULT_NAME(), constans.getT_INIT_USER_ADMIN_DEFAULT_ROL());
                createValueConfigServer(constans.getT_DEFAULT_INIT(), String.format("%s%s", constans.getT_DEFAULT_CREATE(), new Date()));
            } catch (Exception e) {
                r.setCode(0);
                r.setMessage(constans.getMENSAJE_ERROR_INIT_CONFIG());
                r.setResponse(constans.getMENSAJE_ERROR());
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
            }
            r.setCode(1);
            r.setMessage(constans.getMENSAJE_CONFIGURACION_INICIALIZADA());
            r.setResponse(constans.getMENSAJE_OK());
        } else {
            r.setCode(1);
            r.setMessage(constans.getMENSAJE_CONFIGURACION_YA_INICIALIZADA());
            r.setResponse(constans.getMENSAJE_OK());
        }
        return ResponseEntity.ok(r);
    }

    @PostMapping(path = "/createNode", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> crearNodo(@RequestBody RequestNode requestNode) {
        Response tR = createNodeByRequest(Response.class, requestNode);
        return ResponseEntity.status(tR.getCode() != 1 ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED).body(tR);
    }

    @PostMapping(path = "/createDatasource", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> crearDatasource(@RequestBody RequestDataSource requestNode) {
        Response tR = createDataSourceRequest(Response.class, requestNode);
        return ResponseEntity.status(tR.getCode() != 1 ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED).body(tR);
    }

    @PostMapping(path = "/createQueries", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> crearQueries(@RequestBody RequestQueries requestNode) {
        Response tR = createQueriesWithParameters(Response.class, requestNode,0);
        return ResponseEntity.status(tR.getCode() != 1 ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED).body(tR);
    }
    @PostMapping(path = "/createReport", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> crearReport(@RequestBody RequestReport requestNode) {
        Response tR = createReport(Response.class,requestNode);
        return ResponseEntity.status(tR.getCode() != 1 ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED).body(tR);
    }

    @GetMapping(path = "/viewNode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestExport> viewNodo() {
        Map<String,Object> n = new HashMap<>();
        n.put("holis","hola");
        RequestExport r = new RequestExport();
        r.setDoParam(n);
        return ResponseEntity.ok(r);
    }


    private void createInitUser(String name, String value, String defaultMail, String lastName, String firstName, String rol) {
        if (configServerService.countByNameConfigServerEqualsIgnoreCase(name) == 0) {
            User u = new User();
            u.setActive(Boolean.TRUE);
            u.setEmail(defaultMail);
            u.setLastName(lastName);
            u.setName(firstName);
            u.setUserName(firstName);
            u.setPassword(firstName);
            Set<Role> sR = new HashSet<>();
            sR.add(userService.findRolByName(rol));
            u.setRoles(sR);
            userService.saveUser(u);
            createValueConfigServer(name, value);
        }
    }

    private <T> T createReport(Class<T> typeReturn, RequestReport requestReport){
        Reports re = new Reports();
        Response r = new Response();
        r.setCode(0);
        if(requestReport.getReport()!=null && requestReport.getNode()!=null&&requestReport.getQuery()!=null){
            if(requestReport.getReport().length()>0 && requestReport.getNode().length()>0&&requestReport.getQuery().length()>0){
                Optional<Queries> q = queriesService.findByIdEquals(Integer.parseInt(requestReport.getQuery()));
                if(q.isPresent()){
                    TypeResources tTR = typeResourceServiceImple.findByNameTypeResourceEqualsIgnoreCase("folder");
                    NodeResources tNR = nodeResourcesService.findByIdEqualsAndIconEquals(UUID.fromString(requestReport.getNode()),tTR);
                    if(tNR!=null){
                        Resources resources = new Resources();
                        resources.setValueResources(requestReport.getReport().getBytes(StandardCharsets.UTF_8));
                        resourcesService.save(resources);
                        re.setReports(q.get());
                        reportsService.save(re);
                        NodeResources nodeResources = new NodeResources();
                        nodeResources.setNameNodeResources(requestReport.getNameReport());
                        nodeResources.setIsRoot(Boolean.FALSE);
                        nodeResources.setIsFile(Boolean.TRUE);
                        nodeResources.setDescriptionNodeResources(requestReport.getDescReport());
                        nodeResources.setChildrenNodeResources(tNR);
                        nodeResources.setReports(re);
                        nodeResources.setResources(resources);
                        nodeResources.setIcon(typeResourceServiceImple.findByNameTypeResourceEqualsIgnoreCase("report"));
                        nodeResourcesService.save(nodeResources);
                        r.setCode(1);
                        r.setResponse(constans.getMENSAJE_OK());
                        r.setMessage(constans.getMENSAJE_REPORT_CREATE());

                    }else {
                        r.setResponse(constans.getMENSAJE_ERROR());
                        r.setMessage(constans.getMENSAJE_NODO_EXISTENTE());
                    }

                }else{
                    r.setResponse(constans.getMENSAJE_ERROR());
                    r.setMessage(constans.getMENSAJE_QUERY_NO_EXITS());
                }
            }else {
                r.setResponse(constans.getMENSAJE_ERROR());
                r.setMessage(constans.getMENSAJE_CAMPO_LENGTH());
            }
        }else{
            r.setResponse(constans.getMENSAJE_ERROR());
            r.setMessage(constans.getMENSAJE_CAMPO_NULL());
        }
        return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(re);
    }

    private <T> T createNodeByRequest(Class<T> typeReturn, RequestNode requestNode) {
        NodeResources nR = new NodeResources();
        Response r = new Response();
        r.setCode(0);
        try {
            if (requestNode.getNameNode() != null && requestNode.getDescNode() != null && requestNode.getIdParentNode() != null && requestNode.getTypeNode() != null) {
                if (requestNode.getNameNode().length() > 0 && requestNode.getDescNode().length() > 0 && requestNode.getIdParentNode().length() > 0 && requestNode.getTypeNode().length() > 0) {
                    TypeResources tTR = typeResourceServiceImple.findByNameTypeResourceEqualsIgnoreCase(requestNode.getTypeNode());
                    NodeResources tNR = nodeResourcesService.findByIdEqualsAndIconEquals(UUID.fromString(requestNode.getIdParentNode()), tTR);
                    if (tNR != null) {
                        if (nodeResourcesService.countByDescriptionNodeResourcesEqualsIgnoreCaseAndChildrenNodeResourcesEquals(requestNode.getDescNode(), tNR) == 0) {

                            nR.setNameNodeResources(requestNode.getNameNode());
                            nR.setDescriptionNodeResources(requestNode.getDescNode());
                            nR.setIcon(tTR);
                            nR.setChildrenNodeResources(tNR);
                            nR.setIsFile(Boolean.FALSE);
                            nR.setIsRoot(Boolean.FALSE);
                            nodeResourcesService.save(nR);
                            r.setCode(1);
                            r.setResponse(constans.getMENSAJE_OK());
                            r.setMessage(constans.getMENSAJE_CREATE_NODE());
                        } else {
                            r.setResponse(constans.getMENSAJE_ERROR());
                            r.setMessage(constans.getMENSAJE_NODO_EXISTENTE());
                        }
                    } else {
                        r.setResponse(constans.getMENSAJE_ERROR());
                        r.setMessage(constans.getMENSAJE_NODO_EXISTENTE());
                    }
                } else {
                    r.setResponse(constans.getMENSAJE_ERROR());
                    r.setMessage(constans.getMENSAJE_CAMPO_LENGTH());
                }
            } else {
                r.setResponse(constans.getMENSAJE_ERROR());
                r.setMessage(constans.getMENSAJE_CAMPO_NULL());
            }
        } catch (Exception e) {
            e.printStackTrace();
            r.setResponse(constans.getMENSAJE_ERROR());
            r.setMessage(constans.getMENSAJE_SOLICITUD_ERROR());
        }
        return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(nR);
    }

    private <T> T createDataSourceRequest(Class<T> typeReturn, RequestDataSource requestDataSource) {
        DataSource dS = new DataSource();
        Response r = new Response();
        r.setCode(0);
        if (requestDataSource.getPwsDataSource() != null && requestDataSource.getNameDataSource() != null &&
                requestDataSource.getUrlDataSource() != null && requestDataSource.getTypeDataBase() != null
                && requestDataSource.getUsernameDataSource() != null) {
            if (requestDataSource.getNameDataSource().length() > 0 &&
                    requestDataSource.getUrlDataSource().length() > 0 && requestDataSource.getTypeDataBase().length() > 0
                    && requestDataSource.getUsernameDataSource().length() > 0) {
                if (dataSourceService.countByNameDataSourceEqualsIgnoreCase(requestDataSource.getNameDataSource()) == 0) {
                    Optional<TypeDataBase> tTDB = typeDataBaseService.findByNameTypeDataBaseEqualsIgnoreCase(requestDataSource.getTypeDataBase());
                    if (tTDB.isPresent()) {
                        dS.setNameDataSource(requestDataSource.getNameDataSource());
                        dS.setTypeDataBase(tTDB.get());
                        dS.setUrlDataSource(requestDataSource.getUrlDataSource());
                        dS.setUsernameDataSource(requestDataSource.getUsernameDataSource());
                        dS.setPwsDataSource(requestDataSource.getPwsDataSource());
                        dataSourceService.save(dS);
                        if (requestDataSource.getPwsDataSource().length() > 0) {
                            Simmetrics sss = new Simmetrics();
                            Integer index = sss.indexKey(String.valueOf(dS.getId()));
                            sss.addKey(constans.getListCodCifra().get(index));
                            dS.setPwsDataSource(sss.encriptar(requestDataSource.getPwsDataSource()));
                            dataSourceService.save(dS);
                        }
                        r.setCode(1);
                        r.setResponse(constans.getMENSAJE_OK());
                        r.setMessage(constans.getMENSAJE_CREATE_DATA_SOURCE());
                    } else {
                        r.setResponse(constans.getMENSAJE_ERROR());
                        r.setMessage(constans.getMENSAJE_TYPE_DATABASE_NO_EXITS());
                    }
                } else {
                    r.setResponse(constans.getMENSAJE_ERROR());
                    r.setMessage(constans.getMENSAJE_TYPE_DATABASE_EXITS());
                }

            } else {
                r.setResponse(constans.getMENSAJE_ERROR());
                r.setMessage(constans.getMENSAJE_CAMPO_LENGTH());
            }
        } else {
            r.setResponse(constans.getMENSAJE_ERROR());
            r.setMessage(constans.getMENSAJE_CAMPO_NULL());
        }
        return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(dS);
    }

    private <T> T createQueriesWithParameters(Class<T> typeReturn, RequestQueries requestQueries,Integer mode) {
        Queries dS = new Queries();
        Response r = new Response();
        r.setCode(0);
        if (requestQueries.getQueriesBodyExecute() != null && requestQueries.getQueriesBuilt() != null && requestQueries.getQueriesCharactersRemove() != null
                && requestQueries.getQueriesMultiPart() != null
                && requestQueries.getQueriesName() != null && requestQueries.getQueriesSeparatorCharacters() != null
                && requestQueries.getDataSourceId() != null && requestQueries.getTypeExecuteIdBody() != null
                ) {
            if (requestQueries.getQueriesBodyExecute().length() > 0 && requestQueries.getTypeExecuteIdBody().length() > 0 &&
                    requestQueries.getDataSourceId().length() > 0 && requestQueries.getQueriesName().length() > 0
                    && requestQueries.getParametrosQuery().size() > 0) {
                Map<Integer,Optional<DataSource>> optionalMapDS = new HashMap<>();
                Map<Integer,Optional<TypeExecute>> optionalMapTE = new HashMap<>();
                if(requestQueries.getParametrosQuery()!=null){
                    if(requestQueries.getParametrosQuery().size() > 0){
                        for (ParametersDao tPD : requestQueries.getParametrosQuery()
                        ) {
                            if (tPD.getTypeExecuteId() != null && tPD.getParametersQueriesValueFormat() != null
                                    && tPD.getParametersQueriesPosition() != null && tPD.getParametersQueriesName() != null && tPD.getParametersQueriesExecute() != null) {
                                if (tPD.getTypeExecuteId().length() > 0 && tPD.getParametersQueriesValueFormat().length() > 0
                                        && tPD.getParametersQueriesName().length() > 0 && tPD.getParametersQueriesExecute().length() > 0) {
                                    if(tPD.getDataSourceId()!=null){
                                        if(tPD.getDataSourceId().length()>0){
                                            Optional<DataSource> optionalDataSource = dataSourceService.findByNameDataSourceEqualsIgnoreCase(tPD.getDataSourceId());
                                            if(optionalDataSource.isPresent()){
                                                optionalMapDS.put(tPD.getParametersQueriesPosition(),optionalDataSource);
                                            }else{
                                                r.setResponse(constans.getMENSAJE_ERROR());
                                                r.setMessage(constans.getMENSAJE_TYPE_DATASOURCE_NO_EXITS());
                                                return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(dS);
                                            }
                                            Optional<TypeExecute> optionalTypeExecute = typeExecuteService.findByNameTypeExecuteEqualsIgnoreCase(tPD.getTypeExecuteId());
                                            if(optionalTypeExecute.isPresent()){
                                                optionalMapTE.put(tPD.getParametersQueriesPosition(),optionalTypeExecute);
                                            }else {
                                                r.setResponse(constans.getMENSAJE_ERROR());
                                                r.setMessage(constans.getMENSAJE_TYPE_EXECUTE_NO_EXITS());
                                                return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(dS);
                                            }
                                        }
                                    }
                                } else {
                                    r.setResponse(constans.getMENSAJE_ERROR());
                                    r.setMessage(constans.getMENSAJE_CAMPO_LENGTH());
                                    return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(dS);
                                }
                            } else {
                                r.setResponse(constans.getMENSAJE_ERROR());
                                r.setMessage(constans.getMENSAJE_CAMPO_NULL());
                                return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(dS);
                            }
                        }
                    }
                }else {
                    if(mode.equals(1)){
                        return typeReturn.cast(Boolean.TRUE);
                    }
                }

                Optional<DataSource> optionalDataSource = dataSourceService.findByNameDataSourceEqualsIgnoreCase(requestQueries.getDataSourceId());
                Optional<TypeExecute> optionalTypeExecute = typeExecuteService.findByNameTypeExecuteEqualsIgnoreCase(requestQueries.getTypeExecuteIdBody());
                if(optionalDataSource.isPresent()){
                    if(optionalTypeExecute.isPresent()){
                        //dS= createQueries(Queries.class,requestQueries,optionalDataSource.get(),optionalTypeExecute.get(),Boolean.FALSE);
                        dS.setNameQueries(requestQueries.getQueriesName());
                        dS.setTypeQueries(null);
                        dS.setBuilt(requestQueries.getQueriesBuilt());
                        dS.setCharactersRemove(requestQueries.getQueriesCharactersRemove());
                        dS.setSeparatorCharacters(requestQueries.getQueriesSeparatorCharacters());
                        dS.setMultiPart(requestQueries.getQueriesMultiPart());
                        dS.setHeadExecuteDataSource(null);
                        dS.setTypeExecuteHead(null);
                        dS.setBodyExecuteDataSource(requestQueries.getQueriesBodyExecute());
                        dS.setTypeExecuteBody(optionalTypeExecute.get());
                        dS.setDataSource(optionalDataSource.get());
                        queriesService.save(dS);
                        for (ParametersDao tPD : requestQueries.getParametrosQuery()
                        ){
                            ParametersQueries tPQ = new ParametersQueries();
                            Optional<DataSource> tPQds=optionalMapDS.get(tPD.getParametersQueriesPosition());
                            Optional<TypeExecute> tPQTE = optionalMapTE.get(tPD.getParametersQueriesPosition());
                            tPQ.setDataSource(tPQds.isPresent()?tPQds.get():null);
                            tPQ.setTypeExecute(tPQTE.isPresent()?tPQTE.get():null);
                            tPQ.setQueries(dS);
                            tPQ.setPositionParametersQueries(tPD.getParametersQueriesPosition());
                            tPQ.setNameParametersQueries(tPD.getParametersQueriesName());
                            tPQ.setDescriptionParametersQueries(tPD.getParametersQueriesDescription());
                            tPQ.setValueFormatParametersQueries(tPD.getParametersQueriesValueFormat());
                            tPQ.setTypeParametersQueries(tPD.getParametersQueriesType());
                            tPQ.setBodyExecuteParametersQueries(tPD.getParametersQueriesExecute());
                            parametersQueryService.save(tPQ);
                        }
                        r.setCode(1);
                        r.setResponse(constans.getMENSAJE_OK());
                        r.setMessage(constans.getMENSAJE_CREATE_NODE());
                    }else{
                        r.setResponse(constans.getMENSAJE_ERROR());
                        r.setMessage(constans.getMENSAJE_TYPE_EXECUTE_NO_EXITS());
                    }
                }else{
                    r.setResponse(constans.getMENSAJE_ERROR());
                    r.setMessage(constans.getMENSAJE_TYPE_DATASOURCE_NO_EXITS());
                }
            } else {
                r.setResponse(constans.getMENSAJE_ERROR());
                r.setMessage(constans.getMENSAJE_CAMPO_LENGTH());
                if (mode.equals(1))typeReturn.cast(Boolean.FALSE);
            }
        } else {
            r.setResponse(constans.getMENSAJE_ERROR());
            r.setMessage(constans.getMENSAJE_CAMPO_NULL());
            if (mode.equals(1))typeReturn.cast(Boolean.FALSE);
        }
        return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(dS);
    }

    private <T> T createQueries(Class<T> typeReturn, RequestQueries requestQueries,DataSource dS,TypeExecute tE,
                                Boolean validation){
        Queries q = new Queries();
        Response r = new Response();
        r.setCode(0);
         Object tT =validation? createQueriesWithParameters(Boolean.class,requestQueries,1):Boolean.TRUE;
         if(tT instanceof Boolean){
             if((Boolean) tT){
                 q.setNameQueries(requestQueries.getQueriesName());
                 q.setTypeQueries(null);
                 q.setBuilt(requestQueries.getQueriesBuilt());
                 q.setCharactersRemove(requestQueries.getQueriesCharactersRemove());
                 q.setSeparatorCharacters(requestQueries.getQueriesSeparatorCharacters());
                 q.setMultiPart(requestQueries.getQueriesMultiPart());
                 q.setHeadExecuteDataSource(null);
                 q.setTypeExecuteHead(null);
                 q.setBodyExecuteDataSource(requestQueries.getQueriesBodyExecute());
                 q.setTypeExecuteBody(tE);
                 q.setDataSource(dS);
                 queriesService.save(q);
                 System.out.println("paso aqui");
                 r.setResponse(constans.getMENSAJE_OK());
                 r.setMessage(constans.getMENSAJE_CONFIGURACION_INICIALIZADA());
             }else {
                 r.setResponse(constans.getMENSAJE_ERROR());
                 r.setMessage(constans.getMENSAJE_ERROR_VALIDACION_NOESPE());
             }
         }else {
             r.setResponse(constans.getMENSAJE_ERROR());
             r.setMessage(constans.getMENSAJE_ERROR_VALIDACION_NOESPE());
         }
        return (typeReturn.getName().equals(Response.class.getName())) ? typeReturn.cast(r) : typeReturn.cast(q);
    }

    private void createRoles(String name, String value) {
        if (configServerService.countByNameConfigServerEqualsIgnoreCase(name) == 0) {
            for (String rol : constans.getListUserRol()
            ) {
                Role roles = new Role();
                roles.setRole(rol);
                userService.saveRol(roles);
            }
            createValueConfigServer(name, value);
        }
    }

    private void createTypeExecute(String name, String value) {
        if (configServerService.countByNameConfigServerEqualsIgnoreCase(name) == 0) {
            for (Map.Entry<String, String> tempTE : constans.getListTypeExecute().entrySet()
            ) {
                TypeExecute tTE = new TypeExecute();
                tTE.setNameTypeExecute(tempTE.getKey());
                tTE.setValueTypeExecute(tempTE.getValue());
                typeExecuteService.save(tTE);
            }
            createValueConfigServer(name, value);
        }
    }

    private void createTypeDataBase(String name, String value, String nameDefault, String valueDefault) {
        if (configServerService.countByNameConfigServerEqualsIgnoreCase(name) == 0) {
            for (Map.Entry<String, String> type : constans.getListTypeDataBase().entrySet()
            ) {
                TypeDataBase tTDB = new TypeDataBase();
                tTDB.setNameTypeDataBase(type.getValue());
                tTDB.setDriverClassNameTypeDataBase(type.getKey());
                typeDataBaseService.save(tTDB);
            }
            createValueConfigServer(name, value);
            createValueConfigServer(nameDefault, valueDefault);
        }
    }

    private void createResource(String name, String value) {
        if (configServerService.countByNameConfigServerEqualsIgnoreCase(name) == 0) {
            for (String type : constans.getListRootResource()
            ) {
                NodeResources tNR = new NodeResources();
                tNR.setNameNodeResources(type);
                tNR.setDescriptionNodeResources(type);
                tNR.setIcon(typeResourceServiceImple.findByNameTypeResourceEqualsIgnoreCase("FOLDER"));
                tNR.setIsFile(Boolean.FALSE);
                tNR.setIsRoot(Boolean.TRUE);
                tNR.setResources(null);
                tNR.setReports(null);
                tNR.setChildrenNodeResources(null);
                nodeResourcesService.save(tNR);
            }
            createValueConfigServer(name, value);
        }
    }

    private void createTypeResource(String name, String value) {
        if (configServerService.countByNameConfigServerEqualsIgnoreCase(name) == 0) {
            for (String type : constans.getListTypeResource()
            ) {
                TypeResources tTR = new TypeResources();
                tTR.setNameTypeResource(type);
                typeResourceServiceImple.save(tTR);
            }
            createValueConfigServer(name, value);
        }
    }

    private void createValueConfigServer(String name, String value) {
        ConfigServer cS = new ConfigServer();
        for (Map.Entry<String, String> configEnables : constans.getListParameterConfig().entrySet()
        ) {
            cS.setNameConfigServer(configEnables.getKey());
            cS.setValueConfigServer(configEnables.getValue());
            configServerService.save(cS);
            cS = new ConfigServer();
        }
        cS.setNameConfigServer(name);
        cS.setValueConfigServer(value);
        configServerService.save(cS);
    }
}
