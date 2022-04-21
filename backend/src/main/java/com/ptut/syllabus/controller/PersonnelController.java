package com.ptut.syllabus.controller;

import com.ptut.syllabus.entity.Personnel;
import com.ptut.syllabus.payload.response.MessageResponse;

import java.util.Optional;

import com.ptut.syllabus.dao.PersonnelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/api")
public class PersonnelController {
    @Autowired
    private PersonnelRepository personnelDao;
    @Value("${ptut.app.motDePasseDefaut}")
    private String motDePasseDefaut;
    @Autowired
    PasswordEncoder encoder;

    /**
     * Récupérer le semestre, l'année et le statut d'un UE
     * 
     * @param id pour transmettre les informations à la vue
     * @return le semestre, l'année et le statut de l'UE
     */
    @GetMapping(path = "/personnel-detail/")
    public @ResponseBody List<Personnel> personnelsDetail() {
        return personnelDao.findAll();
    }

    @PostMapping(path = "/resilier-mdp")
    public ResponseEntity<?> valeursUE(@RequestParam(name = "id") Integer id) {
        Optional<Personnel> personnel = personnelDao.findById(id);
        if (personnel.isPresent()) {
            Personnel p = personnel.get();
            p.setMotDePasse(encoder.encode(motDePasseDefaut));
            p.setPremiereConnexion(true);
            personnelDao.save(p);
            return ResponseEntity
                    .ok(new MessageResponse("Le mot de passe a été réinitialisé avec succès !"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Le personnel n'a pas été trouvé."));
        }
    }
}