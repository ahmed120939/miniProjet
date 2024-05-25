package com.example.gestion_conge.repository;

import com.example.gestion_conge.entity.Conge;
import com.example.gestion_conge.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {

    @Query(value = "select * from conges order by date_debut desc", nativeQuery = true)
    List<Conge> findAllConge();

    @Query(value = "SELECT * FROM conges c where YEAR(c.date_debut)= ?1 ORDER BY c.date_debut", nativeQuery = true)
    List<Conge> findAllCongeByYear(String year);


    @Query(value = "select * from conges c where c.employe_id = ?1 order by date_debut desc", nativeQuery = true)
    List<Conge> findByEmploye_Id(Long idEmploye);

    @Query(value = "SELECT * FROM conges c where c.employe_id = ?1 and  YEAR(c.date_debut)= ?2 ORDER BY c.date_debut", nativeQuery = true)
    List<Conge> findAllCongeByEmploye_idAndYear(Long idEmploye,String year);


    @Query("SELECT DISTINCT YEAR(c.dateDebut) FROM Conge c ORDER BY YEAR(c.dateDebut)")
    List<Integer> findDistinctYears();
}
