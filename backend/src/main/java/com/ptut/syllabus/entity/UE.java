package com.ptut.syllabus.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Entity // Une entité J

public class UE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String code;

    @NonNull
    private String titre;

    private Integer creditEcts;

    @ManyToOne
    @JsonIgnoreProperties({ "ue" })
    private Semestre semestre;

    @OneToMany(mappedBy = "ue")
    @JsonIgnoreProperties({ "ue" })
    private Set<Cours> cours;

    @ManyToOne
    @NonNull
    @JsonIgnoreProperties({ "responsableDUE" })
    private Personnel responsable;
}
