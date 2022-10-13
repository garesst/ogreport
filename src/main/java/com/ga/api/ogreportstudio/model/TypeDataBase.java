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
@Table(name = "type_data_base")
public class TypeDataBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_data_base_id", nullable = false)
    private Integer id;
    @Column(name = "type_data_base_name",nullable = false)
    @NotEmpty(message = "*Por favor escribe un nombre")
    private String nameTypeDataBase;
    @Column(name = "type_data_base_driver_class_name",nullable = false)
    @NotEmpty(message = "*Por favor escribe un nombre")
    private String driverClassNameTypeDataBase;
}