package com.example.gestion_conge.service;

import com.example.gestion_conge.entity.Conge;
import com.example.gestion_conge.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CongeService {
    CongeRepository congeRepository;

    @Autowired
    public CongeService(CongeRepository congeRepository) {
        this.congeRepository = congeRepository;
    }


    public List<Conge> findAllConge(String year,String etat) {

        List<Conge> listConge;
        if (year != null) {
            listConge = congeRepository.findAllCongeByYear(year);
        } else {
            listConge = congeRepository.findAllConge();
        }
        return listConge.stream().collect(Collectors.toList());


    }

    public List<Integer> findAllDistinctYears() {
        return congeRepository.findDistinctYears();
    }

    public List<Conge> findByIdEmploye(Long idEmploye, String year,String etat) {
        List<Conge> listConge;

        if (year != null) {
            System.out.println("eeeee"+year);
            listConge = congeRepository.findAllCongeByEmploye_idAndYear( idEmploye,year);
        } else {
            System.out.println("xxxxxx"+year);
            listConge = congeRepository.findByEmploye_Id(idEmploye);
        }

        return listConge.stream().collect(Collectors.toList());


    }
}
