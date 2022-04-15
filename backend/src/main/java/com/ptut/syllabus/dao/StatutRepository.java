package com.ptut.syllabus.dao;

import com.ptut.syllabus.entity.Statut;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "statut", path="statut")
public interface StatutRepository extends JpaRepository<Statut, Integer> {


}