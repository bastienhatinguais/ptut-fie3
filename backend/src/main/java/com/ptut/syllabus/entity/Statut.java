package com.ptut.syllabus.entity;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @ToString
@Entity // Une entité JPA

public class Statut {
    @Id
    private Integer id;

    private String intitule;
}
