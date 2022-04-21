package com.ptut.syllabus.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Data;
import lombok.NonNull;

@Data
public class InscriptionRequest {
    @NotBlank
    @Size(min = 3, max = 40)
    private String nom;

    @NotBlank
    @Size(min = 2, max = 40)
    private String prenom;

    @NotBlank
    @Size(min = 3, max = 20)
    private String pseudo;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String motDePasse;
}