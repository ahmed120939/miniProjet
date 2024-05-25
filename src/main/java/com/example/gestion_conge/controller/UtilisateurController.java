package com.example.gestion_conge.controller;

import com.example.gestion_conge.entity.Utilisateur;
import com.example.gestion_conge.service.UtilisateurService;
import jakarta.persistence.DiscriminatorValue;
import jakarta.servlet.http.HttpSession;
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


    @PostMapping("/login")
    public ModelAndView findByLoginAndPassword(@RequestParam(name = "login") String login,
                                               @RequestParam(name = "password") String password,
                                               HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        Utilisateur utilisateur = utilisateurService.findByLoginAndPassword(login, password);
        if (utilisateur != null) {
            String typePersonne = utilisateur.getClass().getAnnotation(DiscriminatorValue.class).value();
            session.setAttribute("idEmploye", utilisateur.getId());
            session.setAttribute("nom", utilisateur.getNom());
            session.setAttribute("prenom", utilisateur.getPrenom());
            session.setAttribute("typePersonne", typePersonne);
            modelAndView.setViewName("redirect:/conge/findAllConge");
        } else {
            modelAndView.addObject("error", "Invalid username or password");
            modelAndView.setViewName("index");
        }

        return modelAndView;
    }

}
