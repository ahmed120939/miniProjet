package com.example.gestion_conge.service;

import com.example.gestion_conge.entity.Conge;
import com.example.gestion_conge.entity.Employe;

import com.example.gestion_conge.enumeration.Etat;
import com.example.gestion_conge.repository.CongeRepository;
import com.example.gestion_conge.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CongeService {
    CongeRepository congeRepository;
    UtilisateurRepository utilisateurRepository;

    @Autowired
    public CongeService(CongeRepository congeRepository, UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.congeRepository = congeRepository;
    }


    public List<Conge> findAllConge(String year, String etat, String employe) {

        List<Conge> listConge;

        if (year != null) {
            if (year.isEmpty()) {
                listConge = congeRepository.findAllConge();
            } else {
                listConge = congeRepository.findAllCongeByYear(year);
            }

        } else if (employe != null) {
            listConge = congeRepository.findCongeByEmploye(employe);
        } else if (etat != null) {
            listConge = congeRepository.findCongeByEtat(etat);
        } else {
            listConge = congeRepository.findAllConge();
        }
        return listConge.stream().collect(Collectors.toList());


    }

    public List<Conge> findAll() {

        List<Conge> listConge;
        listConge = congeRepository.findAll();

        return listConge.stream().collect(Collectors.toList());


    }

    public List<Integer> findAllDistinctYears() {
        return congeRepository.findDistinctYears();
    }

    public List<Conge> findByIdEmploye(Long idEmploye, String year, String etat) {
        List<Conge> listConge;

        if (year != null || year == "") {
            listConge = congeRepository.findAllCongeByEmploye_idAndYear(idEmploye, year);
        } else if (etat != null || etat == "") {
            listConge = congeRepository.findByEmploye_IdAndEtat(idEmploye, etat);
        } else {

            listConge = congeRepository.findByEmploye_Id(idEmploye);
        }

        return listConge.stream().collect(Collectors.toList());


    }

    public boolean getSoldeCongeByEmploye(Long idEmploye) {
        Optional<Employe> utilisateur = utilisateurRepository.getSoldeCongeByEmploye(idEmploye);


        return utilisateur.isEmpty();
    }
    public boolean save(Conge conge,Long idEmploye) {
        Optional<Employe> employe =  utilisateurRepository.getEmployeBuId(idEmploye);
        conge.setEtat(Etat.SOLLICITE);
        conge.setEmploye(employe.get());
        if(conge.getDescription()==null || conge.getDescription().isBlank() ){
            conge.setDescription("conge");
        }
        try {
           congeRepository.saveAndFlush(conge);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean updateEtatSollicite(Long id,String etat) {


        try {
           Conge conge =  congeRepository.findById(id).get();
            conge.setEtat( Etat.valueOf(etat));
            congeRepository.saveAndFlush(conge);
            return true;
        } catch (Exception e) {

            return false;
        }
    }


    public Long findSoldeCongeByEmploye(Long id) {

return congeRepository.findSoldeCongeByEmploye(id);

    }


}
