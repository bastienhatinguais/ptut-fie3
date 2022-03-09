package com.ptut.syllabus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    @ResponseBody
    @RequestMapping(path = "/hello")
    public String sayHello() {
        LOG.info("GET sur /hello");
        return "Salut c'est le backend :3";
    }

}