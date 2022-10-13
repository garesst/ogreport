package com.ga.api.ogreportstudio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.type.UUIDCharType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parameters_queries")
public class ParametersQueries {
    @Id
    @Column(name="parameters_queries_id",updatable = false,nullable = false,columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name = "parameters_queries_position",nullable = false)
    private Integer positionParametersQueries;
    @Column(name = "parameters_queries_name",nullable = false)
    @Length(min = 4)
    @NotEmpty
    private String nameParametersQueries;
    @Column(name = "parameters_queries_description",nullable = false)
    @Length(min = 4)
    @NotEmpty
    private String descriptionParametersQueries;
    @Column(name = "parameters_queries_value_format",nullable = false)
    @Length(min = 2)
    @NotEmpty
    private String valueFormatParametersQueries;
    @Column(name = "parameters_queries_type",nullable = false)
    private String typeParametersQueries;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "queries_id")
    private Queries queries;
    @OneToOne
    @JoinColumn(name = "type_execute_id")
    private TypeExecute typeExecute;
    @Column(name = "parameters_queries_execute",length = 5000,nullable = false)
    private String bodyExecuteParametersQueries;
    @OneToOne
    @JoinColumn(name = "data_source_id")
    private DataSource dataSource;
}