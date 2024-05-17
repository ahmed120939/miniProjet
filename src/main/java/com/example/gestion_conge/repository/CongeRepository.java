package com.example.gestion_conge.repository;

import com.example.gestion_conge.entity.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {
}
