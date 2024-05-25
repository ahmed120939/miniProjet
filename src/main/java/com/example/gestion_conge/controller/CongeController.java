package com.example.gestion_conge.controller;

import com.example.gestion_conge.entity.Conge;
import com.example.gestion_conge.enumeration.Etat;
import com.example.gestion_conge.service.CongeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/conge")
public class CongeController {
    @Autowired
    private final CongeService congeService;


    public CongeController(CongeService congeService) {
        super();
        this.congeService = congeService;
    }


    @GetMapping("/findAllConge")
    public ModelAndView findAllConge(HttpSession session,
                                     @RequestParam(name = "year", required = false) String year,
     @RequestParam(name = "etat", required = false) String etat
    ) {
        Long idEmploye = (Long) session.getAttribute("idEmploye");
        String typePersonne = (String) session.getAttribute("typePersonne");
        String nom = (String) session.getAttribute("nom");
        String prenom = (String) session.getAttribute("prenom");

        ModelAndView modelAndView = new ModelAndView();
        List<Conge> listConges;

            List<Etat> etats= Arrays.asList(Etat.values());

        List<Integer> years = congeService.findAllDistinctYears();

        if ("administrateur".equals(typePersonne)) {

            listConges = congeService.findAllConge(year,etat);
        } else {
            listConges = congeService.findByIdEmploye(idEmploye,year,etat);
        }


        modelAndView.addObject("conges", listConges);
        modelAndView.addObject("etats", etats);
        modelAndView.addObject("exercices", years);
        modelAndView.addObject("nomPrenom", nom+" "+prenom);

        modelAndView.setViewName("conge/conges");

        return modelAndView;
    }



}
