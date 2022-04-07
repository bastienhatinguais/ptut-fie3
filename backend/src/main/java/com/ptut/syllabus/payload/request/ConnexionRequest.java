package com.ptut.syllabus.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnexionRequest {
    @NotBlank
    private String pseudo;

    @NotBlank
    private String motDePasse;
}