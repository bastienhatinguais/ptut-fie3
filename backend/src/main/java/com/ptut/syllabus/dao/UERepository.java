package com.ptut.syllabus.dao;


import java.util.Optional;
import com.ptut.syllabus.entity.UE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ue", path="ue")
public interface UERepository extends JpaRepository<UE, Integer> {
    
    default UE semestreAnneeStatutFromUE(Integer id){
        Optional<UE> ue = findById(id);
        return ue.get();
    } 
}
