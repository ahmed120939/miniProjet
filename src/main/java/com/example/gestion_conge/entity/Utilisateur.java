package com.example.gestion_conge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Inheritance(strategy = InheritanceType. TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE_PERSONNE")
@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

}
