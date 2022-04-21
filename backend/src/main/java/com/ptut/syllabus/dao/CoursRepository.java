package com.ptut.syllabus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

import com.ptut.syllabus.entity.*;

@RepositoryRestResource(collectionResourceRel = "cours", path = "cours")
public interface CoursRepository extends JpaRepository<Cours, Integer> {
    Optional<Cours> findById(Integer id);

}