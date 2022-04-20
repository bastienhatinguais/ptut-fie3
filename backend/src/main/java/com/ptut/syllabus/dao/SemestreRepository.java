
package com.ptut.syllabus.dao;

import com.ptut.syllabus.entity.Semestre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "semestre", path = "semestre")
public interface SemestreRepository extends JpaRepository<Semestre, Integer> {

}