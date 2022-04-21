package com.ptut.syllabus.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Setter
public class PremiereConnexionRequest {
    @NonNull
    private Integer id;

    @NotBlank
    private String motDePasse;
}