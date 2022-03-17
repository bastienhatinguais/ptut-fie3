package com.ptut.syllabus.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String pseudo;
    private String email;
    private String nom;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String pseudo, String email, String nom, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.nom = nom;
        this.roles = roles;
    }
}