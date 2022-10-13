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
@Table(name = "type_execute")
public class TypeExecute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_execute_id", nullable = false)
    private Integer id;
    @Column(name = "type_execute_name", nullable = false)
    @NotEmpty()
    private String nameTypeExecute;
    @Column(name = "type_execute_value", nullable = false)
    @NotEmpty()
    private String valueTypeExecute;
}