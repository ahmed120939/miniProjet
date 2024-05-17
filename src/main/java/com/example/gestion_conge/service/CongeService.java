package com.example.gestion_conge.service;

import com.example.gestion_conge.repository.CongeRepository;
import com.example.gestion_conge.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CongeService {
    CongeRepository congeRepository;
    @Autowired
    public CongeService(CongeRepository congeRepository) {
        this.congeRepository = congeRepository;
    }
}
