package com.example.gestion_conge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)

    private String code;

    @Column(nullable = false, length = 50)

    @Size(max = 50)
    private String nom;

    @Column(nullable = false, length = 50)

    @Size(max = 50)
    private String prenom;

    @Column(nullable = false)
    @NotNull
    private LocalDate dateEmbauchement;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String login;

    @Column(nullable = false, length = 10)
    @NotBlank
    @Size(min = 6, max = 10)
    private String password;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Conge> conges ;





}
