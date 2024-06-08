package com.example.gestion_conge.controller;


import com.example.gestion_conge.entity.Conge;
import com.example.gestion_conge.enumeration.Etat;
import com.example.gestion_conge.service.CongeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
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
                                     @RequestParam(name = "etat", required = false) String etat,
                                     @RequestParam(name = "employe", required = false) String searchemploye
    ) {
        Long idEmploye = (Long) session.getAttribute("idEmploye");
        String typePersonne = (String) session.getAttribute("typePersonne");
        String nom = (String) session.getAttribute("nom");
        String prenom = (String) session.getAttribute("prenom");

        ModelAndView modelAndView = new ModelAndView();
        List<Conge> listConges;

        List<Etat> etats = Arrays.asList(Etat.values());

        List<Integer> years = congeService.findAllDistinctYears();

        if ("administrateur".equals(typePersonne)) {

            listConges = congeService.findAllConge(year, etat, searchemploye);
        } else {
            listConges = congeService.findByIdEmploye(idEmploye, year, etat);
            modelAndView.addObject("idEmploye", idEmploye);
        }

        modelAndView.addObject("year", year);

        modelAndView.addObject("etatSelected", etat);
        modelAndView.addObject("employeSelected", searchemploye);
        modelAndView.addObject("typePersonne", typePersonne);
        modelAndView.addObject("conges", listConges);
        modelAndView.addObject("etats", etats);
        modelAndView.addObject("exercices", years);
        modelAndView.addObject("nomPrenom", nom + " " + prenom);

        modelAndView.setViewName("conge/conges");

        return modelAndView;
    }


    @GetMapping("/detailsConge")
    public ModelAndView detailsConge(HttpSession session) {
        Long idEmploye = (Long) session.getAttribute("idEmploye");
        String nom = (String) session.getAttribute("nom");
        String prenom = (String) session.getAttribute("prenom");
        Long nombreConge = congeService.findSoldeCongeByEmploye(idEmploye);


        Long  nombreCongeRes= Long.valueOf(30);
        if (nombreConge != null){
            nombreCongeRes-=nombreConge;
        }else{
            nombreConge= Long.valueOf(0);
        }



        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("conge", new Conge());
        modelAndView.addObject("dataObtenu", nombreConge);
        modelAndView.addObject("dataRestant", nombreCongeRes);
        modelAndView.addObject("nomPrenom", nom + " " + prenom);
        modelAndView.setViewName("conge/add_conge");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addConge(HttpSession session, @ModelAttribute Conge conge) throws ParseException {

        Long idEmploye = (Long) session.getAttribute("idEmploye");
        congeService.save(conge, idEmploye);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/conge/findAllConge");
        return modelAndView;
    }


    @GetMapping("/verifSoldeConge/{id}")
    public ResponseEntity<Boolean> verifSoldeConge(@PathVariable Long id) {
        boolean soldeSuffisant = congeService.getSoldeCongeByEmploye(id);
        return ResponseEntity.ok(soldeSuffisant);
    }


    @PostMapping("/updateEtatSollicite/{id}")
    public ResponseEntity<Boolean> updateEtatSollicite(@PathVariable Long id,
                                                       @RequestParam(name = "etat") String etat
    ) {
        boolean result = congeService.updateEtatSollicite(id, etat);
        return ResponseEntity.ok(result);

    }
}
