package com.carometro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carometro.model.Experiencia;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {

}
