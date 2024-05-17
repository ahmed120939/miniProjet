package com.example.gestion_conge.controller;

import com.example.gestion_conge.enumeration.Etat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/etats")
public class EtatController {

    @GetMapping
    public Etat[] getAllEtats() {
        return Etat.values();
    }
}