package com.example.gestion_conge.repository;

import com.example.gestion_conge.entity.Conge;
import com.example.gestion_conge.entity.Employe;
import com.example.gestion_conge.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    Optional<Utilisateur> findByLoginAndPassword(String login, String password);

    @Query(value = "select * from employes where id= ?1 and date_embauchement <= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)", nativeQuery = true)
    Optional<Employe> getSoldeCongeByEmploye(Long id);

    @Query(value = "select * from employes where id= ?1", nativeQuery = true)
    Optional<Employe> getEmployeBuId(Long id);




}
