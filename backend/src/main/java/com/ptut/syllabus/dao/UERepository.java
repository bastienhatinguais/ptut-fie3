package com.ptut.syllabus.dao;


import com.ptut.syllabus.entity.UE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ue", path="ue")
public interface UERepository extends JpaRepository<UE, Integer> {

    // récuperer le semestre d'un UE
    //@Query(value = "SELECT ue.code, ue.titre, ue.creditEcts, semestre.titre AS semestre, annee.intitule AS annee, statut.intitule AS statut" +
    //"FROM ue INNER JOIN semestre INNER JOIN annee INNER JOIN statut WHERE ue.id=unUE", 
    //nativeQuery = true)
    //UEResult valeursUE(Integer unUE);

    // récuperer l'année d'un UE

    // récuperer le statut d'un UE

}
