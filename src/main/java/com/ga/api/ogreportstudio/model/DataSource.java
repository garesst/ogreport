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
@Table(name = "data_source")
public class DataSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_source_id", nullable = false)
    private Integer id;
    @Column(name = "data_source_name",nullable = false,unique = true)
    @Length(min = 5, message = "*El nombre debe tener mas de 5 caracteres")
    @NotEmpty(message = "*Por favor escribe un nombre")
    private String nameDataSource;
    @OneToOne
    @JoinColumn(name = "type_data_base_id")
    private TypeDataBase typeDataBase;
    @Column(name = "data_source_url",nullable = false)
    @Length(min = 5, message = "*Debe tener mas de 5 caracteres")
    @NotEmpty(message = "*Por favor escribe un dato")
    private String urlDataSource;
    @Column(name = "data_source_username",nullable = false)
    @Length(min = 1, message = "*Debe tener mas de 1 caracteres")
    @NotEmpty(message = "*Por favor escribe un dato")
    private String usernameDataSource;
    @Column(name = "data_source_psw",nullable = false)
    private String pwsDataSource;
}