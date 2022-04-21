package com.ptut.syllabus.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ptut.syllabus.entity.Personnel;
import com.ptut.syllabus.entity.Role;
import com.ptut.syllabus.payload.request.ConnexionRequest;
import com.ptut.syllabus.payload.request.InscriptionRequest;
import com.ptut.syllabus.payload.request.PremiereConnexionRequest;
import com.ptut.syllabus.payload.response.JwtResponse;
import com.ptut.syllabus.payload.response.MessageResponse;
import com.ptut.syllabus.dao.PersonnelRepository;
import com.ptut.syllabus.dao.RoleRepository;
import com.ptut.syllabus.security.jwt.JwtUtils;
import com.ptut.syllabus.security.services.UtilisateurDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Value("${ptut.app.motDePasseDefaut}")
    private String motDePasseDefaut;

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
                userDetails.getPrenom(),
                roles,
                userDetails.getPremiereConnexion()));
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> registerUser(@Valid @RequestBody InscriptionRequest signUpRequest) {
        if (personnelRepository.existsByPseudo(signUpRequest.getPseudo())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Ce pseudo est déjà pris !"));
        }
        if (personnelRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Cet email est déjà utilisé !"));
        }

        // Create new personnel's account
        Personnel personnel;
        if (signUpRequest.getMotDePasse() != null) {
            personnel = new Personnel(signUpRequest.getNom(), signUpRequest.getPrenom(),
                    signUpRequest.getPseudo(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getMotDePasse()), false);
        } else {
            personnel = new Personnel(signUpRequest.getNom(), signUpRequest.getPrenom(),
                    signUpRequest.getPseudo(),
                    signUpRequest.getEmail(),
                    encoder.encode(motDePasseDefaut), true);
        }
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role personnelRole = roleRepository.findByNom(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(personnelRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByNom(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByNom(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role personnelRole = roleRepository.findByNom(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(personnelRole);
                }
            });
        }
        personnel.setRoles(roles);
        personnel.setPremiereConnexion(true);

        Personnel p = personnelRepository.save(personnel);
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !", p.getId()));
    }

    @PostMapping("/premiere-connexion")
    public ResponseEntity<?> premiereConnexion(@Valid @RequestBody PremiereConnexionRequest premiereConnexionRequest) {
        Optional<Personnel> personnel = personnelRepository.findById(premiereConnexionRequest.getId());

        if (!personnel.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Cet utilisateur n'est pas connu."));
        }
        Personnel p = personnel.get();
        if (!p.getPremiereConnexion()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Ce n'est pas votre première connexion !"));
        }

        p.setMotDePasse(encoder.encode(premiereConnexionRequest.getMotDePasse()));
        p.setPremiereConnexion(false);
        personnelRepository.save(p);
        return ResponseEntity.ok(new MessageResponse("Le mot de passe a bien été changé !", null));
    }
}