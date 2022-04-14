package com.ptut.syllabus.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
    private Set<Cours> responsableDesCours;

    @ManyToMany(mappedBy = "intervenants")
    private Set<Cours> donneLesCours;
}