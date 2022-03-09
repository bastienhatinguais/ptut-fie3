package com.ptut.syllabus.dao;

import com.ptut.syllabus.entity.UE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ue", path="ue")
public interface UERepository extends JpaRepository<UE, Integer> {


}
