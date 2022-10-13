package com.ga.api.ogreportstudio.dao.node_dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestNode {

    String nameNode;
    String idParentNode;
    String descNode;
    String typeNode;
    Boolean is_file=Boolean.FALSE;
    Boolean is_root=Boolean.FALSE;
    String reports_id=null;
    String resource_id=null;
}
