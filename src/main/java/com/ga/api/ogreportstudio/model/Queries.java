package com.ga.api.ogreportstudio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "queries")
public class Queries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queries_id")
    private int id;
    @Column(name = "queries_name",nullable = false)
    @Length(min = 5, message = "*El nombre debe tener mas de 5 caracteres")
    @NotEmpty(message = "*Por favor escribe un nombre")
    private String nameQueries;
    @OneToOne
    @JoinColumn(name = "type_queries_id")
    private TypeQueries typeQueries;
    @Column(name = "queries_built",nullable = false,columnDefinition = "tinyint(1) default true")
    private Boolean built=Boolean.FALSE;
    @Column(name = "queries_characters_remove",columnDefinition = "varchar(50) default '[]{}'")
    private String charactersRemove="[]{}";
    @Column(name = "queries_separator_characters",length = 1,columnDefinition = "varchar(50) default ','")
    @Length(max = 1)
    private String separatorCharacters=",";
    @Column(name = "queries_multi_part",nullable = false,columnDefinition = "tinyint(1) default false")
    private Boolean multiPart=Boolean.FALSE;
    @Column(name = "queries_head_execute",length = 5000)
    private String headExecuteDataSource;
    @OneToOne
    @JoinColumn(name = "type_execute_id")
    private TypeExecute typeExecuteHead;
    @Column(name = "queries_body_execute",length = 5000,nullable = false)
    private String bodyExecuteDataSource;
    @OneToOne
    @JoinColumn(name = "type_execute_id_body",referencedColumnName = "type_execute_id")
    private TypeExecute typeExecuteBody;
    @OneToOne
    @JoinColumn(name = "data_source_id")
    private DataSource dataSource;
    @OneToMany(mappedBy = "queries")
    private List<ParametersQueries> parametersQuerieses = new ArrayList<>();
}
