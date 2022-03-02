package com.ptut.syllabus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private int creditsEcts;

    private int nbHeureCM;

    private int nbHeureTD;

    private int nbHeureTP;

    private String description;

    private String objectifs;

    private String prerequis;

    private String competences;

    private String modalitesEvaluation;
}