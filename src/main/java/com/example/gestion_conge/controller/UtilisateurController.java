package com.example.gestion_conge.controller;

import com.example.gestion_conge.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        super();
        this.utilisateurService = utilisateurService;
    }



    /*@PostMapping(value = "/login")
    public String save(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.save(utilisateur);
        return "redirect:/clients/all";
    }*/


    @PostMapping("/login")
    public ModelAndView save(@RequestParam(name = "login") String login,

                             @RequestParam(name = "password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        utilisateurService.findByLoginAndPassword(login, password);
        modelAndView.setViewName("home"); // la vue home.html

        return modelAndView;
    }

}
