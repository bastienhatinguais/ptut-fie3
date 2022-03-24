package com.ptut.syllabus.dao;

import com.ptut.syllabus.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findByPseudoOrEmail(String pseudo, String email);

    Optional<Utilisateur> findByPseudo(String pseudo);

    Boolean existsByPseudo(String pseudo);

    Boolean existsByEmail(String email);
}