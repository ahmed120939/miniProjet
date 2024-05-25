package com.example.gestion_conge.service;


import com.example.gestion_conge.entity.Utilisateur;
import com.example.gestion_conge.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur findByLoginAndPassword(String login, String password) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByLoginAndPassword(login, password);
        return utilisateur.orElse(null);
    }


}
