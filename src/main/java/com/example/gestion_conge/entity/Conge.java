package com.example.gestion_conge.entity;


import com.example.gestion_conge.enumeration.Etat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "conges")
public class Conge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String description;


    @NotNull
    @Column(name = "date_debut")
    private LocalDate dateDebut;


    @NotNull
    private LocalDate dateFin;

    @Column
    private LocalDate dateRupture;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "employe_id", nullable = false)

    private Employe employe;


}