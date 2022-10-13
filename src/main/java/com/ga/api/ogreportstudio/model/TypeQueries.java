package com.ga.api.ogreportstudio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "type_queries")
public class TypeQueries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_queries_id", nullable = false)
    private Integer id;
    @Column(name = "type_queries_name")
    @NotEmpty()
    private String nameTypeDataSource;
    @Column(name = "type_queries_description")
    @NotEmpty()
    private String descriptionTypeDataSource;
    @Column(name = "type_queries_value")
    @NotEmpty()
    private String valueTypeDataSource;
}