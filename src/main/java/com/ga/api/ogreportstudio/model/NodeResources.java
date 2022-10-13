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
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "node_resources")
public class NodeResources {
    @Id
    @Column(name="id",updatable = false,nullable = false,columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name = "text",nullable = false)
    @Length(min = 1, message = "*El nombre debe tener mas de 5 caracteres")
    @NotEmpty(message = "*Por favor escribe un nombre")
    private String nameNodeResources;
    @Column(name = "description",nullable = false)
    @Length(min = 1, message = "*La description debe tener mas de 5 caracteres")
    @NotEmpty(message = "*Por favor escribe una description")
    private String descriptionNodeResources;
    @ManyToOne
    @JoinColumn(name = "icon")
    private TypeResources icon;
    @OneToMany(mappedBy = "childrenNodeResources")
    private List<NodeResources> parentsChildrenNodeResources;
    @ManyToOne
    @JoinColumn(name = "parents")
    private NodeResources childrenNodeResources;
    @Column(name = "isFile",nullable = false,columnDefinition = "tinyint(1) default false")
    private Boolean isFile;
    @Column(name = "isRoot",nullable = false,columnDefinition = "tinyint(1) default false")
    private Boolean isRoot;
    @OneToOne
    @JoinColumn(name = "resources_id")
    private Resources resources;
    @OneToOne
    @JoinColumn(name = "reports_id")
    private Reports reports;
}
