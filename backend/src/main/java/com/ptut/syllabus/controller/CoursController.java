package com.ptut.syllabus.controller;

import java.util.Optional;

import com.ptut.syllabus.dao.CoursRepository;
import com.ptut.syllabus.entity.Cours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class CoursController {
    @Autowired
    private CoursRepository coursDao;

    /**
     * Récupérer le semestre, l'année et le statut d'un UE
     * 
     * @param id pour transmettre les informations à la vue
     * @return le semestre, l'année et le statut de l'UE
     */
    @GetMapping(path = "/coursUeSemestre/{id}")
    public @ResponseBody Cours valeursUE(@PathVariable(value = "id") Integer id) {
        Optional<Cours> cours = coursDao.findById(id);
        if (cours.isPresent()) {
            return cours.get();
        } else {
            return null;
        }
    }
}
