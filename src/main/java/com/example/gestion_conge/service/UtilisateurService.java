package com.example.gestion_conge.service;


import com.example.gestion_conge.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UtilisateurService {

    UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Boolean findByLoginAndPassword(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {

        System.out.println("aaaa " + password);
        if (utilisateurRepository.findByLoginAndPassword(login,
                password).isEmpty()) {
            return false;
        } else return true;

    }


}
