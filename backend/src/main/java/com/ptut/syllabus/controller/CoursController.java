package com.ptut.syllabus.controller;

import com.ptut.syllabus.dao.CoursRepository;
import com.ptut.syllabus.entity.Cours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.models.Model;

@RestController
@RequestMapping("/cours")
public class CoursController {

    @Autowired
    private CoursRepository cityDao;
}