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
@Table(name = "config_server")
public class ConfigServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_server_id", nullable = false)
    private Integer id;
    @Column(name = "config_server_name",nullable = false)
    @Length(min = 3)
    @NotEmpty()
    private String nameConfigServer;
    @Column(name = "config_server_value",nullable = false)
    @Length(min = 3)
    @NotEmpty()
    private String valueConfigServer;

}
