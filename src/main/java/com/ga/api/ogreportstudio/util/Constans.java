package com.ga.api.ogreportstudio.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Data
public class Constans {
    private final String MENSAJE_ERROR_INIT_CONFIG = "Ocurrio un error al inicializar la configuración, revise su configuración de BD y vuelva a intentar";
    private final String MENSAJE_ERROR = "NO OK :(";
    private final String MENSAJE_CONFIGURACION_INICIALIZADA = "La configuracion fue inicializada";
    private final String MENSAJE_OK = "OK";
    private final String CODE_CIFRADO_ONE = "$eCR3T1T0";
    private final String CODE_CIFRADO_TWO = "C0MeT3UN$N1CK3RS->W4TCHTV";
    private final String CODE_CIFRADO_THREE = "UNR1V4LeD->AO<3";
    private final String CODE_CIFRADO_FOUR = "M3S3L0SNUM3R0S3N!NGL3S:)";
    private final String CODE_CIFRADO_FIVE = "S3pt3mberW1TH0UTfAp->0";
    private final String CODE_CIFRADO_SIX = "Ale*4ndr40SteN<3";
    private final String CODE_CIFRADO_SEVEN = "H4SL0P0R3L$4LaR!0";
    private final String CODE_CIFRADO_OCHO = "DU4_L!Pa_OR_B1lL!3_E!LI$H";
    private final String CODE_CIFRADO_NIKE = "0L1V!A_R0DRiGO->Go0D_4Y0U";
    private final String CODE_CIFRADO_ZERO = "Th!$iSD4y_THATW!lLt3LlMEyes";
    private final String T_INIT_TYPE_RESOURCE = "init_type_resource";
    private final String T_INIT_RESOURSE = "init_resource";
    private final String T_INIT_TYPE_EXECUTE = "init_type_execute";
    private final String T_INIT_USER_ROLES = "init_user_roles";
    private final String T_INIT_USER_ADMIN = "init_user_admin";
    private final String T_INIT_USER_ADMIN_DEFAULT_EMAIL = "admin@ogresport.com.sv";
    private final String T_INIT_USER_ADMIN_DEFAULT_LASTNAME = "serve";
    private final String T_INIT_USER_ADMIN_DEFAULT_NAME = "admin";
    private final String T_INIT_USER_ADMIN_DEFAULT_ROL = "ADMIN";
    private final String T_TYPE_DATA_BASE = "init_type_data_base";
    private final String T_TYPE_DATA_BASE_DEFAULT = "init_type_data_base_default";
    private final String T_DEFAULT_INIT = "init";
    private final String MENSAJE_NODO_EXISTENTE = "No se pudo crear ya existe ese nombre en el nodo";
    private final String MENSAJE_TYPE_DATABASE_NO_EXITS = "El Type DataBase no existe";
    private final String MENSAJE_TYPE_DATASOURCE_NO_EXITS = "Uno o mas DataSource no existe";
    private final String MENSAJE_TYPE_EXECUTE_NO_EXITS = "El tipo de ejecucion no existe";
    private final String MENSAJE_ERROR_VALIDACION_NOESPE = "Ocurrio un error de validacion no especificado";
    private final String MENSAJE_TYPE_DATABASE_EXITS = "El Type DataBase ya existe";
    private final String MENSAJE_CAMPO_NULL = "Hay campos con valor null";
    private final String MENSAJE_QUERY_NO_EXITS = "La query no existe";
    private final String MENSAJE_REPORT_CREATE = "Reporte creado";
    private final String MENSAJE_CAMPO_LENGTH = "Hay campos que no cumplen la longitud";
    private final String MENSAJE_SOLICITUD_ERROR = "Ocurrio un error, valida y vuelve a intentar";
    private final String MENSAJE_CREATE_NODE = "El nodo se creo correctamente";
    private final String MENSAJE_CREATE_DATA_SOURCE = "El Datasource se creo correctamente";
    private final String T_DEFAULT_CREATE = "create: ";
    HashMap<Integer, String> listCodCifra = new HashMap<Integer, String>() {{
        put(1, CODE_CIFRADO_ZERO);
        put(1, CODE_CIFRADO_ONE);
        put(2, CODE_CIFRADO_TWO);
        put(3, CODE_CIFRADO_THREE);
        put(4, CODE_CIFRADO_FOUR);
        put(5, CODE_CIFRADO_FIVE);
        put(6, CODE_CIFRADO_SIX);
        put(7, CODE_CIFRADO_SEVEN);
        put(8, CODE_CIFRADO_OCHO);
        put(9, CODE_CIFRADO_NIKE);
    }};
    private final String MENSAJE_CONFIGURACION_YA_INICIALIZADA = "Ya existe una configuracion inicializada";
    private final List<String> listTypeResource = Arrays.asList("Folder", "FILE", "REPORT", "IMAGE", "JSON");
    private final List<String> listRootResource = Arrays.asList("ROOT", "REPORT");
    private final List<String> listUserRol = Arrays.asList("ADMIN", "USER_REPORT", "USER_VIEW");
    HashMap<String, String> listTypeExecute = new HashMap<String, String>() {{
        put("SEC", "SELECT");
        put("EXE", "EXECUTE");
        put("JUR", "JSON_URL");
        put("ARP", "API_REST_POST");
        put("ARG", "API_REST_GET");
    }};
    HashMap<String, String> listParameterConfig = new HashMap<String, String>() {{
        put("multipar", "false");
    }};

    HashMap<String, String> listTypeDataBase = new HashMap<String, String>() {{
        put("com.mysql.cj.jdbc.Driver", "MYSQL");
        put("org.mariadb.jdbc.Driver", "MARIADB");
        put("org.postgresql.Driver", "POSTGRESQL");
        put("oracle.jdbc.driver.OracleDriver", "ORACLE");
        put("org.h2.Driver", "H2");
        put("com.microsoft.sqlserver.jdbc.SQLServerDriver", "MSSQL");
        put("com.ga.jsonql.Driver", "JSONQL");
        put("com.ga.jsonql.Bearver.Driver", "JSONQLBEARVER");
        put("com.ga.jsonql.login.Driver", "JSONQLLOGIN");
    }};
    private final String T_TYPE_DATA_BASE_DEFAULT_VALUE = listTypeDataBase.get("com.ga.jsonql.Driver");
}
