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
@Table(name = "type_resource")
public class TypeResources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_resource_id", nullable = false,unique = true)
    private Integer id;
    @Column(name = "type_resource_name",nullable = false)
    @NotEmpty(message = "*Por favor escribe un nombre")
    private String nameTypeResource;
}
