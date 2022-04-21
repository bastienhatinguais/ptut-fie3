package com.ptut.syllabus.controller;

import com.ptut.syllabus.entity.Semestre;
import com.ptut.syllabus.entity.UE;

import java.util.Optional;

import com.ptut.syllabus.dao.SemestreRepository;
import com.ptut.syllabus.dao.UERepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class SemestreController {

    @Autowired
    private SemestreRepository semestreDao;

    /**
     * Récupérer le semestre, l'année et le statut d'un UE
     * 
     * @param id pour transmettre les informations à la vue
     * @return le semestre, l'année et le statut de l'UE
     */
    @GetMapping(path = "/semestre-detail/{id}")
    public @ResponseBody Semestre valeursUE(@PathVariable(value = "id") Integer id) {
        Optional<Semestre> semestre = semestreDao.findById(id);
        if (semestre.isPresent()) {
            return semestre.get();
        } else {
            return null;
        }
    }
}