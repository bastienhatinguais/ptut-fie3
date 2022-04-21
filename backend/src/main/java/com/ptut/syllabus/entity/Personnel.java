package com.ptut.syllabus.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity // Une entité JPA
@Table(name = "personnels", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "pseudo" }),
        @UniqueConstraint(columnNames = { "email" })
})

public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @NonNull
    private String pseudo;

    @NonNull
    private String email;

    @NonNull
    @JsonIgnore
    private String motDePasse;

    @NonNull
    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean premiereConnexion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "personnel_roles", joinColumns = @JoinColumn(name = "personnel_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "responsable")
    @JsonIgnoreProperties({ "responsable", "cours" })
    @JsonIgnore
    private Set<Cours> responsableDesCours;

    @OneToMany(mappedBy = "responsable")
    @JsonIgnore
    private Set<UE> responsableDUE;

    @ManyToMany(mappedBy = "intervenants")
    @JsonIgnore
    @JsonIgnoreProperties({ "intervenants", "cours" })
    private Set<Cours> donneLesCours;
}