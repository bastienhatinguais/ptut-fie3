package com.ptut.syllabus.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder.Default;

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

    @ColumnDefault("0")
    private int nbHeureCM;

    @ColumnDefault("0")
    private int nbHeureTD;

    @ColumnDefault("0")
    private int nbHeureTP;

    @ColumnDefault("0")
    private int nbHeureFOAD;

    private String description;

    private String objectifs;

    private String planDuCours;

    private String competences;

    private String modalitesEvaluation;

    @ManyToOne
    @NonNull
    private Personnel responsable;

    @ManyToMany
    private Set<Personnel> intervenants;
}