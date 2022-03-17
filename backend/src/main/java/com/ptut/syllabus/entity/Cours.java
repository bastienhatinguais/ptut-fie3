package com.ptut.syllabus.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    @NonNull
    private String titre;

    @NonNull
    private int creditsEcts;

    @ColumnDefault("0")
    private int nbHeureCM;

    @ColumnDefault("0")
    private int nbHeureTD;

    @ColumnDefault("0")
    private int nbHeureTP;

    private String description;

    private String objectifs;

    private String prerequis;

    private String competences;

    private String modalitesEvaluation;

    @ManyToOne
    @NonNull
    private Personnel responsable;

    @ManyToMany
    private Set<Personnel> intervenants;
}