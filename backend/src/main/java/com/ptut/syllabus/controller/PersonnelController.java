package com.ptut.syllabus.controller;

import com.ptut.syllabus.entity.Personnel;

import java.util.Optional;

import com.ptut.syllabus.dao.PersonnelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/api")
public class PersonnelController {

    @Autowired
    private PersonnelRepository personnelDao;

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
}