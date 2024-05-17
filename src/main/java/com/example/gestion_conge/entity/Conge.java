package com.example.gestion_conge.entity;


import com.example.gestion_conge.enumeration.Etat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "conges")
public class Conge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String description;


    @NotNull
    private LocalDate dateDebut;


    @NotNull
    private LocalDate dateFin;

    @Column
    private LocalDate dateRupture;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @JsonIgnore
    private Utilisateur utilisateur;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Conge conge = (Conge) o;
        return getId() != null && Objects.equals(getId(), conge.getId());
    }

}