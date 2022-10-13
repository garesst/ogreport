package com.ga.api.ogreportstudio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_name")
    @Length(min = 5, message = "*Tu usuario debe de tener mas de 5 caracteres")
    @NotEmpty(message = "*Por favor escribe un usuario")
    private String userName;
    @Column(name = "email")
    @Email(message = "*Por favor escribe un correo valido")
    @NotEmpty(message = "*Por favor escribe un correo")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Tu contraseña debe de tener mas de 5 caracteres")
    @NotEmpty(message = "*Por favor escribe una contraseña")
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*Por favor escribe tu nombre")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "*Por favor escribe tu apellido")
    private String lastName;
    @Column(name = "active",nullable = false,columnDefinition = "tinyint(1) default true")
    private Boolean active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
