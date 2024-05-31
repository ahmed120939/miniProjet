package com.example.gestion_conge.repository;

import com.example.gestion_conge.entity.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@EnableJpaRepositories
@Repository
public interface CongeRepository extends JpaRepository<Conge, Long> {

    @Query(value = "select * from conges order by date_debut desc", nativeQuery = true)
    List<Conge> findAllConge();

    @Query(value = "SELECT * FROM conges c where YEAR(c.date_debut)= ?1 ORDER BY c.date_debut", nativeQuery = true)
    List<Conge> findAllCongeByYear(String year);

    @Query(value = "SELECT * FROM conges c where c.etat= ?1 ORDER BY c.date_debut", nativeQuery = true)
    List<Conge> findCongeByEtat(String etat);


    @Query(value = "select c.* from conges c left join employes e on e.id = c.employe_id where e.nom like %:searchEmp% or e.prenom like %:searchEmp% order by c.date_debut desc", nativeQuery = true)
    List<Conge> findCongeByEmploye(@Param("searchEmp") String searchEmp);


    @Query(value = "select * from conges c where c.employe_id = ?1 order by date_debut desc", nativeQuery = true)
    List<Conge> findByEmploye_Id(Long idEmploye);
    @Query(value = "select * from conges c where c.employe_id = ?1 and c.etat = ?2 order by date_debut desc", nativeQuery = true)
    List<Conge> findByEmploye_IdAndEtat(Long idEmploye,String etat);

    @Query(value = "SELECT * FROM conges c where c.employe_id = ?1 and  YEAR(c.date_debut)= ?2 ORDER BY c.date_debut", nativeQuery = true)
    List<Conge> findAllCongeByEmploye_idAndYear(Long idEmploye, String year);


    @Query("SELECT DISTINCT YEAR(c.dateDebut) FROM Conge c ORDER BY YEAR(c.dateDebut)")
    List<Integer> findDistinctYears();


    @Modifying
    @Transactional
    @Query(value = "UPDATE conges SET etat = 'EN_COURS' WHERE date_debut = CURDATE() AND etat = 'VALIDE'", nativeQuery = true)
    void updateEtatEnCoursEmploye();
 @Modifying
    @Transactional
    @Query(value = "UPDATE conges SET etat = 'FINI' WHERE date_fin < CURDATE() AND etat = 'EN_COURS'", nativeQuery = true)
    void updateEtatFinEmploye();

    @Query(value = "SELECT SUM(\n" +
            "    CASE\n" +
            "        WHEN date_rupture IS NOT NULL THEN DATEDIFF(date_rupture, date_debut) + 1\n" +
            "        ELSE DATEDIFF(date_fin, date_debut) + 1\n" +
            "    END\n" +
            ") AS total_conges\n" +
            "FROM conges\n" +
            "WHERE employe_id = ?1 \n" +
            "AND date_debut >= DATE_FORMAT(CURDATE() ,'%Y-01-01')\n" +
            "AND date_fin <= DATE_FORMAT(CURDATE() ,'%Y-12-31')",nativeQuery = true)
    Long findSoldeCongeByEmploye(Long idEmploye);


}
