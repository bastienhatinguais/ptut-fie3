package com.ptut.syllabus.dao;


import com.ptut.syllabus.entity.Annee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "annee", path="annee")
public interface AnneeRepository extends JpaRepository<Annee, Integer> {
    
}