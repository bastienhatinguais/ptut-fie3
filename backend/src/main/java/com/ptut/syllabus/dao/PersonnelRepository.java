package com.ptut.syllabus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

import com.ptut.syllabus.entity.*;

@RepositoryRestResource(collectionResourceRel = "personnel", path = "personnel")
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    Optional<Personnel> findByEmail(String email);

    Optional<Personnel> findByPseudoOrEmail(String pseudo, String email);

    Optional<Personnel> findByPseudo(String pseudo);

    Boolean existsByPseudo(String pseudo);

    Boolean existsByEmail(String email);

    List<Personnel> findAll();
}