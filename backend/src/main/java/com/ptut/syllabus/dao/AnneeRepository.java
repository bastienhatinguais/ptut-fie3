package com.ptut.syllabus.dao;

import java.util.List;

import com.ptut.syllabus.entity.Annee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "annee", path="annee")
public interface AnneeRepository extends JpaRepository<Annee, Integer> {

    // récuperer les semestres d'une année
    //@Query(value = "SELECT semestre.titre, semestre.id FROM semestre INNER JOIN annee WHERE annee.intitule=uneAnnee", 
    //nativeQuery = true)
    //List<SemestreDansAnneeResult> semestreDansAnnee(String uneAnnee);
    
}