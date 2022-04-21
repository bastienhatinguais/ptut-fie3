package com.ptut.syllabus.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String pseudo;
    private String email;
    private String nom;
    private String prenom;
    private List<String> roles;
    private Boolean premiereConnexion;

    public JwtResponse(String accessToken, Integer id, String pseudo, String email, String nom, String prenom,
            List<String> roles, Boolean premiereConnexion) {
        this.token = accessToken;
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.nom = nom;
        this.nom = prenom;
        this.roles = roles;
        this.premiereConnexion = premiereConnexion;
    }
}