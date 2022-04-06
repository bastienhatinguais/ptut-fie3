package com.ptut.syllabus.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ptut.syllabus.entity.ERole;
import com.ptut.syllabus.entity.Role;
import com.ptut.syllabus.entity.Utilisateur;
import com.ptut.syllabus.payload.request.ConnexionRequest;
import com.ptut.syllabus.payload.request.InscriptionRequest;
import com.ptut.syllabus.payload.response.JwtResponse;
import com.ptut.syllabus.payload.response.MessageResponse;
import com.ptut.syllabus.dao.RoleRepository;
import com.ptut.syllabus.dao.UtilisateurRepository;
import com.ptut.syllabus.security.jwt.JwtUtils;
import com.ptut.syllabus.security.services.UtilisateurDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UtilisateurRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/connexion")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody ConnexionRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPseudo(), loginRequest.getMotDePasse()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UtilisateurDetailsImpl userDetails = (UtilisateurDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getNom(),
                roles));
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> registerUser(@Valid @RequestBody InscriptionRequest signUpRequest) {
        if (userRepository.existsByPseudo(signUpRequest.getPseudo())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Ce pseudo est déjà pris !"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Cet email est déjà utilisé !"));
        }

        // Create new user's account
        Utilisateur user = new Utilisateur(signUpRequest.getNom(), signUpRequest.getPseudo(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getMotDePasse()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles != null) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "directeur_etude":
                        Role directeurEtudeRole = roleRepository.findByNom(ERole.ROLE_DIRECTEUR_ETUDES)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(directeurEtudeRole);
                        break;
                    case "responsable_annee":
                        Role responsableAnneeRole = roleRepository.findByNom(ERole.ROLE_RESPONSABLE_ANNEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(responsableAnneeRole);
                        break;
                    case "responsable_cours":
                        Role responsableCoursRole = roleRepository.findByNom(ERole.ROLE_RESPONSABLE_COURS)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(responsableCoursRole);
                        break;
                    default:

                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));
    }
}