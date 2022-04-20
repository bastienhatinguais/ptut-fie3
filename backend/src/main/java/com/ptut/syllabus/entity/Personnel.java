package com.ptut.syllabus.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity // Une entité JPA
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    @ColumnDefault("false")
    private boolean estDirecteur;

    private String nom;

    private String prenom;

    @OneToMany(mappedBy = "responsable")
    @JsonIgnoreProperties({"cours"})
    private Set<Cours> responsableDesCours;

    @OneToMany(mappedBy = "responsable")
    @JsonIgnoreProperties({"ue"})
    private Set<UE> responsableDUE;

    @ManyToMany(mappedBy = "intervenants")
    @JsonIgnoreProperties({"cours"})
    private Set<Cours> donneLesCours;
}