package com.ptut.syllabus.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ptut.syllabus.entity.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UtilisateurDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String pseudo;
    private String email;
    private String nom;
    @JsonIgnore
    private String motDePasse;
    private Collection<? extends GrantedAuthority> authorities;

    public UtilisateurDetailsImpl(Long id, String pseudo, String email, String nom, String motDePasse,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.authorities = authorities;
    }

    public static UtilisateurDetailsImpl build(Utilisateur user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNom().name()))
                .collect(Collectors.toList());
        return new UtilisateurDetailsImpl(
                user.getId(),
                user.getPseudo(),
                user.getEmail(),
                user.getNom(),
                user.getMotDePasse(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return pseudo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UtilisateurDetailsImpl user = (UtilisateurDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}