package com.ptut.syllabus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ptut.syllabus.entity.*;

@RepositoryRestResource(collectionResourceRel = "personnel", path = "personnel")
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

}