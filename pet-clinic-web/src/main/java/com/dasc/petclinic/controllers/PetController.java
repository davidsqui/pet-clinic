package com.dasc.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pets")
public class PetController {

    @RequestMapping({"","/index","/index.html"})
    public String listPets() {

        return "pets/index";
    }
}
