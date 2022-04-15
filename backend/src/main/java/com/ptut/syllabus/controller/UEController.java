package com.ptut.syllabus.controller;
import com.ptut.syllabus.entity.UE;

import com.ptut.syllabus.dao.UERepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class UEController {

	@Autowired
	private UERepository UEdao;

	/**
	 * Récupérer le semestre, l'année et le statut d'un UE
	 * 
	 * @param id pour transmettre les informations à la vue
	 * @return le semestre, l'année et le statut de l'UE
	 */
	@GetMapping(path = "/ueAnneeSemestre/{id}")
	public @ResponseBody UE valeursUE(@PathVariable(value="id") Integer id) {
        UE result = UEdao.semestreAnneeStatutFromUE(id);
        return result;
	}
}